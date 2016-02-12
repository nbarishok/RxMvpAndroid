package com.onemanparty.rxmvpandroid.core.persistance;

/**
 * Factory for providing an instance of object graph for fragment
 */
public interface ComponentCreator<C> {
    C create();
}
