package com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.serializable;

import com.onemanparty.rxmvpandroid.core.persistence.viewstate.base.AbsSelfRestorableNavigationLceViewStateImpl;
import com.onemanparty.rxmvpandroid.core.persistence.viewstate.base.PendingStateChange;
import com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.serializable.storage.ViewStateStorage;
import com.onemanparty.rxmvpandroid.core.utils.lambda.Action1;
import com.onemanparty.rxmvpandroid.core.utils.lambda.Action2;
import com.onemanparty.rxmvpandroid.core.view.LceView;
import com.onemanparty.rxmvpandroid.core.view.view_model.EmptyViewModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Implementation of ViewState for LceView with view-based navigation and ability to auto-save itself
 * in {@link ViewStateStorage}.
 * For particular cases of saving instance use {@link StorageBackedNavigationLceViewStateImpl#save()}
 * To restore actual state from storage use {@link StorageBackedNavigationLceViewStateImpl#restore()}
 *
 * When ViewState is no longer needed clean the storage with {@link StorageBackedNavigationLceViewStateImpl#clean()}:
 * - onDestroy() for example
 */
public class StorageBackedNavigationLceViewStateImpl<D extends Serializable & EmptyViewModel, E extends Enum<E>, V extends LceView<D, E>> extends AbsSelfRestorableNavigationLceViewStateImpl<D, E, V, Serializable> {

    private final ViewStateStorage storage;

    public StorageBackedNavigationLceViewStateImpl(ViewStateStorage storage) {
        this.storage = storage;
    }

    @Override
    public void setStateShowContent() {
        super.setStateShowContent();
        save();
    }

    @Override
    public void setStateShowError(E error, boolean isShown) {
        super.setStateShowError(error, isShown);
        save();
    }

    @Override
    public <S extends Serializable> void addToPending(Action2<V, S> op, S data) {
        SerializablePendingStateChangeWithDataImpl<V, S> dto = new SerializablePendingStateChangeWithDataImpl<>();
        dto.setData(data);
        dto.setOperation(op::invoke);
        addPendingStateChange(dto);
    }

    @Override
    public void addToPending(Action1<V> op) {
        SerializablePendingStateChangeImpl<V> dto = new SerializablePendingStateChangeImpl<>();
        dto.setOperation(op::apply);
        addPendingStateChange(dto);
    }

    protected void onPendingStateChangesListAdded() {
        save();
    }

    @Override
    public void save() {
        Observable.fromCallable(() -> {
            storage.save(objectOutputStream -> {
                try {
                    save(objectOutputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return null;
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    /**
     * Executes synchronously now for ease of use
     * It is supposed to be executed when app is recreated after low-memory
     */
    public void restore() {
        Observable.fromCallable(() -> {
            storage.restore(objectInputStream -> {
                try {
                    restoreFromBackUp(objectInputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
            return null;
        }).subscribe();
    }

    @Override
    public void clean() {
        storage.cleanUp();
    }

    private void save(ObjectOutputStream stream) throws IOException {
        stream.writeObject(currentState);
        stream.writeObject(error);
        stream.writeObject(data);
        stream.writeObject(pendingStateChangesList);
    }

    public void restoreFromBackUp(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        currentState = (int) stream.readObject();
        error = (E) stream.readObject();
        data = (D) stream.readObject();
        List<PendingStateChange<V>> pendingStateChangeList = (List<PendingStateChange<V>>) stream.readObject();
        setPendingStateChangeList(pendingStateChangeList);
    }

}
