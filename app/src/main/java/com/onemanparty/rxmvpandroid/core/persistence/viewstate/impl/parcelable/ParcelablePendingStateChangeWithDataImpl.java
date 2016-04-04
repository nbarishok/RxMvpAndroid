package com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import com.onemanparty.rxmvpandroid.core.utils.lambda.ParcelableTransactionAction2;

/**
 * Implementation of pending view-based navigation with ability to put in parcel with param
 * Param should also be parcelable
 */
public class ParcelablePendingStateChangeWithDataImpl<V, D extends Parcelable> implements ParcelablePendingStateChange<V> {

    private D data;
    private ParcelableTransactionAction2<V, D> op;

    public ParcelablePendingStateChangeWithDataImpl() {
    }

    public void setData(D data) {
        this.data = data;
    }

    public void setOperation(ParcelableTransactionAction2<V, D> op) {
        this.op = op;
    }

    @Override
    public void apply(V view) {
        op.invoke(view, data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(op);
        dest.writeSerializable(data.getClass());
        dest.writeParcelable(data, 0);
    }

    protected ParcelablePendingStateChangeWithDataImpl(Parcel in) {
        this.op = (ParcelableTransactionAction2<V, D>) in.readSerializable();
        Class<?> dataType = (Class<?>) in.readSerializable();
        this.data = in.readParcelable(dataType.getClassLoader());
    }

    public static final Parcelable.Creator<ParcelablePendingStateChangeWithDataImpl> CREATOR = new Parcelable.Creator<ParcelablePendingStateChangeWithDataImpl>() {
        public ParcelablePendingStateChangeWithDataImpl createFromParcel(Parcel source) {
            return new ParcelablePendingStateChangeWithDataImpl(source);
        }

        public ParcelablePendingStateChangeWithDataImpl[] newArray(int size) {
            return new ParcelablePendingStateChangeWithDataImpl[size];
        }
    };
}
