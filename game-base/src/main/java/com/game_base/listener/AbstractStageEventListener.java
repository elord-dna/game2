package com.game_base.listener;

import com.game_base.base.FightRole;

/**
 * Created by Administrator on 2017/4/27 0027.
 */
public abstract class AbstractStageEventListener implements IEventListener {

    public abstract void onGainHealth(FightRole role, int val);
    public abstract void onLoseHealth(FightRole role, int val);
    public abstract void onCauseDamage(FightRole role, int val);
    public abstract void onBeDamaged(FightRole role, int val);
    public abstract void onGetHurt(FightRole role, int val);
}
