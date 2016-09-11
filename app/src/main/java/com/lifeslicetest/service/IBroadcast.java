package com.lifeslicetest.service;

public interface IBroadcast {

    boolean hasEventHandlers(Class<?> c);

    boolean isRegistered(Object o);

    void register(Object o);

    void unregister(Object o);

    <T> T getStickyEvent(Class<T> c);

    void removeStickyEvent(Class<?> c);

    void postEvent(Object event);

    void postStickyEvent(Object event);
}
