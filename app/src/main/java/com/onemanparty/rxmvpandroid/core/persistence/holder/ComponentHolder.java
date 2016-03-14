package com.onemanparty.rxmvpandroid.core.persistence.holder;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton object to hold a map of object graphs for app screens
 */
public class ComponentHolder {

    /**
     * Instance
     */
    static volatile ComponentHolder singleton = null;

    /**
     * Map of object graphs
     */
    private Map<String, Object> mComponentMap;

    /**
     * Get singleton instance
     * @return instance
     */
    public static ComponentHolder getInstance() {
        if (singleton == null) {
            synchronized (ComponentHolder.class) {
                if (singleton == null) {
                    singleton = new ComponentHolder();
                }
            }
        }
        return singleton;
    }

    private ComponentHolder() {
        this.mComponentMap = new HashMap<>();
    }

    /**
     * Put object graph
     * @param s id
     * @param component object graph
     * @param <C> type of object graph
     */
    public <C> void putComponent(String s, C component) {
        mComponentMap.put(s, component);
    }

    /**
     * Get object graph
     * @param s id
     * @return object graph
     */
    public <C> C getComponent(String s) {
        return (C) mComponentMap.get(s);
    }

    /**
     * Remove object graph
     * @param s id
     */
    public void remove(String s) {
        mComponentMap.remove(s);
    }

}
