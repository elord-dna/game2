package com.game_base.base.event;

import com.game_base.base.FightRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
public class EventContext {
    private FightRole currentObj; // 触发当前对象
    private FightRole execObj;     // 执行对象
    private FightRole aimObj;      // 目标对象
    private FightRole causeObj;   // 原因对象
    private FightEvent event;       // 当前触发事件

    private int val = 0;                // 触发值
    private Map<FromTo, EventVal> valMap = new HashMap<>();

    public FightRole getCurrentObj() {
        return currentObj;
    }

    public void setCurrentObj(FightRole currentObj) {
        this.currentObj = currentObj;
    }

    public FightRole getExecObj() {
        return execObj;
    }

    public void setExecObj(FightRole execObj) {
        this.execObj = execObj;
    }

    public FightRole getAimObj() {
        return aimObj;
    }

    public void setAimObj(FightRole aimObj) {
        this.aimObj = aimObj;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public FightRole getCauseObj() {
        return causeObj;
    }

    public void setCauseObj(FightRole causeObj) {
        this.causeObj = causeObj;
    }

    public FightEvent getEvent() {
        return event;
    }

    public void setEvent(FightEvent event) {
        this.event = event;
    }

    /**
     * 清空内部所有数据
     */
    public void clearAll() {
        currentObj = null;
        aimObj = null;
        causeObj = null;
        event = null;
        valMap.clear();
    }

    public Map<FromTo, EventVal> getValMap() {
        return valMap;
    }

    public void setValMap(Map<FromTo, EventVal> valMap) {
        this.valMap = valMap;
    }

    public int getVal(FromTo fromTo, ValueType valueType) {
        int val = 0;
        if (valMap.containsKey(fromTo)) {
            val = valMap.get(fromTo).getVal(valueType).intValue();
        }
        return val;
    }

    public void setVal(FromTo fromTo, ValueType valueType, int val) {
        if (!valMap.containsKey(fromTo)) {
            valMap.put(fromTo, new EventVal());
        }
        valMap.get(fromTo).setVal(valueType, val);
    }

    public void addVal(FromTo fromTo, ValueType valueType, int val) {
        if (!valMap.containsKey(fromTo)) {
            valMap.put(fromTo, new EventVal());
        }
        valMap.get(fromTo).addVal(valueType, val);
    }

    public Map<FightRole, Map<FightEvent, List<CallAction>>> getEventMap() {
        EventManager eventManager = EventManager.getInstance();
        return eventManager.getEventMap();
    }
}
