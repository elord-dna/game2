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
    // IMPROVE 重新设计，目前还存放了一些关于stage的信息，可以写为stageContext，这里精简为只涉及时间参数
    private static EventContext eventContext;

    static {
        if (eventManager == null) {
            eventManager = new EventManager();
            eventMap = new HashMap<>();
            eventContext = new EventContext();
        }
    }

    public static EventManager getInstance() {
        if (eventManager == null) {
            eventManager = new EventManager();
            eventMap = new HashMap<>();
            eventContext = new EventContext();
        }
        return eventManager;
    }

    public Map<FightRole, Map<FightEvent, List<CallAction>>> getEventMap() {
        if (eventMap == null) {
            eventMap = new HashMap<>();
        }
        return eventMap;
    }

    private List<CallAction> getCallActions(FightRole role, FightEvent event) {
        List<CallAction> lists = null;
        Map<FightEvent, List<CallAction>> eventList = getEventMap().get(role);
        if (eventList != null && !eventList.isEmpty()) {
            lists = eventList.get(event);
        }
        return lists;
    }

    private void setCallActions(FightRole role, FightEvent event, List<CallAction> lists) {
        Map<FightEvent, List<CallAction>> eventList = getEventMap().get(role);
        if (eventList == null) {
            eventList = new HashMap<>();
            eventList.put(event, lists);
            getEventMap().put(role, eventList);
        } else {
            eventList.put(event, lists);
        }
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
        eventListMap.put(event, callActions);
        eventMap.put(role, eventListMap);
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
        List<CallAction> callActions = getCallActions(role, event);
        if (callActions == null) {
            return;
        }
        List<CallAction> copy = new ArrayList<>(callActions);
        Collections.copy(copy, callActions);
//        callActions.clear();
        // 让复制遍历，新加入的buff就会下次运行了
        copy.forEach(Callback::call);
        callActions.removeIf(callAction -> {
            return !callAction.isEffective();
        });

//        copy.addAll(callActions);
//        setCallActions(role, event, copy);
    }
}
