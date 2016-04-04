package com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.serializable.storage;

import com.onemanparty.rxmvpandroid.core.utils.lambda.Action1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface ViewStateStorage {

    void save(Action1<ObjectOutputStream> action);

    void restore(Action1<ObjectInputStream> action);

    void cleanUp();
}