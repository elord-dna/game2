package com.game_base.base.event;

import com.game_base.base.FightRole;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
public class FromTo {
    private FightRole from;
    private FightRole to;

    public FromTo(FightRole self) {
        this.from = self;
        this.to = self;
    }

    public FromTo(FightRole from, FightRole to) {
        this.from = from;
        this.to = to;
    }

    public FightRole getFrom() {
        return from;
    }

    public void setFrom(FightRole from) {
        this.from = from;
    }

    public FightRole getTo() {
        return to;
    }

    public void setTo(FightRole to) {
        this.to = to;
    }
}
