package com.game_base.base.event;

import com.game_base.base.FightRole;

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

    public void destory() {
        eventMap.clear();
    }

    public void remove(FightRole role) {
        eventMap.remove(role);
    }

    public void bindObj(int val) {

    }
}
