package com.onemanparty.rxmvpandroid.core.persistence;

/**
 * Factory for providing an instance of object graph for fragment
 */
public interface ComponentCreator<C> {
    C create();
}
