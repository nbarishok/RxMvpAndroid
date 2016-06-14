package com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import com.onemanparty.rxmvpandroid.core.persistence.viewstate.base.AbsNavigationLceViewStateImpl;
import com.onemanparty.rxmvpandroid.core.utils.lambda.Action1;
import com.onemanparty.rxmvpandroid.core.utils.lambda.Action2;
import com.onemanparty.rxmvpandroid.core.view.LceView;

import java.util.ArrayList;

/**
 * Implementation of ViewState for LceView with view-based navigation and ability to put itself
 * (the whole object) in Parcel
 */
public class ParcelableDataNavigationLceViewStateImpl<D extends Parcelable, E extends Enum<E>, V extends LceView<D, E>>
                extends AbsNavigationLceViewStateImpl<D, E, V, Parcelable>
                implements Parcelable{

    @Override
    public <S extends Parcelable> void addToPending(Action2<V, S> op, S data) {
        ParcelablePendingStateChangeWithDataImpl<V, S> dto = new ParcelablePendingStateChangeWithDataImpl<>();
        dto.setData(data);
        dto.setOperation(op::invoke);
        addPendingStateChange(dto);
    }

    @Override
    public void addToPending(Action1<V> op) {
        ParcelablePendingStateChangeImpl<V> dto = new ParcelablePendingStateChangeImpl<>();
        dto.setOperation(op::apply);
        addPendingStateChange(dto);
    }

    public static final Creator<ParcelableDataNavigationLceViewStateImpl> CREATOR = new Creator<ParcelableDataNavigationLceViewStateImpl>() {
        @Override
        public ParcelableDataNavigationLceViewStateImpl createFromParcel(Parcel in) {
            return new ParcelableDataNavigationLceViewStateImpl(in);
        }

        @Override
        public ParcelableDataNavigationLceViewStateImpl[] newArray(int size) {
            return new ParcelableDataNavigationLceViewStateImpl[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        writeLcePart(out, flags);
        if (pendingStateChangesList == null || pendingStateChangesList.size() == 0) {
            out.writeInt(0);
            return;
        }
        int size = pendingStateChangesList.size();
        out.writeInt(size);

        for (int i = 0; i < size; i++) {
            ParcelablePendingStateChange<V> item = (ParcelablePendingStateChange<V>) pendingStateChangesList.get(i);
            out.writeSerializable(item.getClass());
            out.writeParcelable(item, 0);
        }
    }

    private void writeLcePart(Parcel dest, int flags) {
        dest.writeInt(this.currentState);
        if (error != null) {
            dest.writeInt(1);
            dest.writeSerializable(error.getClass());
            dest.writeString(error.name());
        } else {
            dest.writeInt(0);
        }
        if (data != null) {
            dest.writeInt(1);
            dest.writeSerializable(this.data.getClass());
            dest.writeParcelable(this.data, 0);
        } else {
            dest.writeInt(0);
        }
    }

    protected ParcelableDataNavigationLceViewStateImpl(Parcel in) {
        readLcePart(in);
        pendingStateChangesList = new ArrayList<>();
        int size = in.readInt();

        for (int i = 0; i < size; i++) {
            Class<?> aClass = (Class<?>) in.readSerializable();
            Parcelable parcelable = in.readParcelable(aClass.getClassLoader());
            pendingStateChangesList.add((ParcelablePendingStateChange<V>) parcelable);
        }
    }

    private void readLcePart(Parcel in) {
        this.currentState = in.readInt();
        int hasEnum = in.readInt();
        if (hasEnum == 1) {
            Class<E> errorType = (Class<E>) in.readSerializable();
            String err = in.readString();
            this.error = Enum.valueOf(errorType, err);
        }

        int hasData = in.readInt();
        if (hasData == 1) {
            Class<D> dataType = (Class<D>) in.readSerializable();
            this.data = in.readParcelable(dataType.getClassLoader());
        }
    }
}
