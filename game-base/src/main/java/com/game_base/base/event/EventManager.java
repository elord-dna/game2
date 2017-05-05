package com.game_base.base.event;

import com.game_base.base.FightRole;

import java.util.*;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
public class EventManager {
    private static EventManager eventManager;
    // 仅作为战斗用的事件管理器
    private static Map<FightRole, Map<FightEvent, List<CallAction>>> eventMap;
    private static EventContext eventContext;

    public static EventManager getInstance() {
        if (eventManager == null) {
            eventManager = new EventManager();
            eventMap = new HashMap<>();
            eventContext = new EventContext();
        }
        return eventManager;
    }

    public Map<FightRole, Map<FightEvent, List<CallAction>>> getEventMap() {
        return eventMap;
    }
    public EventContext getEventContext() {
        return eventContext;
    }

    public void destory() {
        eventMap.clear();
    }

    public void remove(FightRole role) {
        eventMap.remove(role);
    }

    public void bindObj(int val) {

    }

    public void removeCallback(Callback callback) {
        // TODO
    }

    private void bindAction(FightRole role, FightEvent event, CallAction callAction) {
        Map<FightEvent, List<CallAction>> eventListMap;
        if (eventMap.containsKey(role)) {
            eventListMap = eventMap.get(role);
        } else {
            eventListMap = new HashMap<>();
        }
        List<CallAction> callActions;
        if (eventListMap.containsKey(event)) {
            callActions = eventListMap.get(event);
        } else {
            callActions = new LinkedList<>();
        }
        callActions.add(callAction);
    }

    public void on(FightRole role, FightEvent event, Callback callback) {
        bindAction(role, event, new CallAction(callback));
    }

    public void on(FightRole role, FightEvent event, Callback callback, int times) {
        bindAction(role, event, new CallAction(callback, times));
    }

    public void one(FightRole role, FightEvent event, Callback callback) {
        bindAction(role, event, new CallAction(callback, 1));
    }

    public void triggle(FightRole role, FightEvent event) {
        List<CallAction> callActions = eventMap.get(role).get(event);
        callActions.forEach(Callback::call);
        callActions.removeIf(callAction -> {
            return !callAction.isEffective();
        });
    }
}
