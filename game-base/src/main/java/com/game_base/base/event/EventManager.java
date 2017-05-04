package com.game_base.base.event;

import com.game_base.base.FightRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
public class EventManager {
    private static EventManager eventManager;
    // 仅作为战斗用的事件管理器
    private static Map<FightRole, Map<FightEvent, List<Callback>>> eventMap;
    private static EventContext eventContext;

    public static EventManager getInstance() {
        if (eventManager == null) {
            eventManager = new EventManager();
            eventMap = new HashMap<>();
            eventContext = new EventContext();
        }
        return eventManager;
    }

    public Map<FightRole, Map<FightEvent, List<Callback>>> getEventMap() {
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

    public void bindAction(FightRole role, FightEvent event, Callback callback) {
        Map<FightEvent, List<Callback>> eventListMap;
        if (eventMap.containsKey(role)) {
            eventListMap = eventMap.get(role);
        } else {
            eventListMap = new HashMap<>();
        }
        List<Callback> callbacks;
        if (eventListMap.containsKey(event)) {
            callbacks = eventListMap.get(event);
        } else {
            callbacks = new ArrayList<>();
        }
        callbacks.add(callback);
    }

    public void one(FightRole role, FightEvent event, Callback callback) {

    }

    public void triggle(FightRole role, FightEvent event) {
        List<Callback> callbacks = eventMap.get(role).get(event);
        callbacks.forEach(Callback::call);
    }
}
