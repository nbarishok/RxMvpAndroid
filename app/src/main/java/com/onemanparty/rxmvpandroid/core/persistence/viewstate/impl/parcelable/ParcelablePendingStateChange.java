package com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.parcelable;

import android.os.Parcelable;

import com.onemanparty.rxmvpandroid.core.persistence.viewstate.base.PendingStateChange;

/**
 * Pending view-based navigation with ability to put in parcel
 */
public interface ParcelablePendingStateChange<V> extends PendingStateChange<V>, Parcelable {
}
