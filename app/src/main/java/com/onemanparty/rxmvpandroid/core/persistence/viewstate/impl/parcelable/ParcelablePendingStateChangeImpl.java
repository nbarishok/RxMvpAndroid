package com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.parcelable;


import android.os.Parcel;
import android.os.Parcelable;

import com.onemanparty.rxmvpandroid.core.utils.lambda.Action1;

/**
 * Implementation of pending view-based navigation with ability to put in parcel without params
 */
public class ParcelablePendingStateChangeImpl<V> implements ParcelablePendingStateChange<V> {

    private Action1<V> op;

    public void setOperation(Action1<V> op) {
        this.op = op;
    }

    @Override
    public void apply(V view) {
        op.apply(view);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.op);
    }

    public ParcelablePendingStateChangeImpl() {
    }

    private ParcelablePendingStateChangeImpl(Parcel in) {
        this.op = (Action1<V>) in.readSerializable();
    }

    public static final Parcelable.Creator<ParcelablePendingStateChangeImpl> CREATOR = new Parcelable.Creator<ParcelablePendingStateChangeImpl>() {
        public ParcelablePendingStateChangeImpl createFromParcel(Parcel source) {
            return new ParcelablePendingStateChangeImpl(source);
        }

        public ParcelablePendingStateChangeImpl[] newArray(int size) {
            return new ParcelablePendingStateChangeImpl[size];
        }
    };
}
