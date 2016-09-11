package com.lifeslicetest.service.impl;


import com.lifeslicetest.service.IBroadcast;

import org.greenrobot.eventbus.EventBus;

public class EventBusBroadcastImpl implements IBroadcast {

    private EventBus eventBus;

    public EventBusBroadcastImpl(EventBus bus) {
        eventBus = bus;
    }

    @Override
    public boolean hasEventHandlers(Class<?> c) {
        return eventBus.hasSubscriberForEvent(c);
    }

    @Override
    public boolean isRegistered(Object o) {
        return eventBus.isRegistered(o);
    }

    @Override
    public void register(Object o) {
        eventBus.register(o);
    }

    @Override
    public void unregister(Object o) {
        eventBus.unregister(o);
    }

    @Override
    public <T> T getStickyEvent(Class<T> c) {
        return eventBus.getStickyEvent(c);
    }

    @Override
    public void removeStickyEvent(Class<?> c) {
        eventBus.removeStickyEvent(c);
    }

    @Override
    public void postEvent(Object event) {
        eventBus.post(event);
    }

    @Override
    public void postStickyEvent(Object event) {
        eventBus.postSticky(event);
    }
}
