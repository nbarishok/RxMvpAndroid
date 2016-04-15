package com.onemanparty.rxmvpandroid.core.persistence.viewstate.error_declaration;

/**
 * Types of possible view - errors
 * Based on this type there is a strategy for resolving saving error state
 */
public enum ErrorTypes {
    /**
     * If shown, don't save error to view state
     * Once shown, clean error state
     */
    ONE_SHOT,
    /**
     * save error state until new (successful) request will clean error state
     */
    DEFAULT
}
