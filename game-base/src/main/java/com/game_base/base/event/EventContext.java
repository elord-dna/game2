package com.game_base.base.event;

import com.game_base.base.FightRole;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
public class EventContext {
    private FightRole currentObj; // 触发当前对象
    private FightRole execObj;     // 执行对象
    private FightRole aimObj;      // 目标对象
    private FightRole causeObj;   // 原因对象

    private int val = 0;                // 触发值

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
}
