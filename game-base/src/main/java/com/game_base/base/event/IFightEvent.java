package com.game_base.base.event;

import com.game_base.base.FightRole;
import com.game_base.listener.AbstractStageEventListener;
import com.game_base.stage.StageManager;

/**
 * Created by Administrator on 2017/4/27 0027.
 */
public interface IFightEvent {
    AbstractStageEventListener stageListener = StageManager.getInstance().getStageEventListener();
    EventManager eventManager = EventManager.getInstance();
    default void gainHealth(FightRole role, int val) {
        stageListener.onGainHealth(role, val);
    }

    default void loseHealth(FightRole role, int val) {

    }

    default void causeDamage(FightRole role, int val) {

    }

    default void beDamaged(FightRole role, int val) {

    }

    default void getHurt(FightRole role, int val) {
        stageListener.onGetHurt(role, val);
        // FIXME

    }

    void on(FightEvent event, Callback callback);

    void one(FightEvent event, Callback callback);

    void triggle(FightEvent event);

    void triggle(StageEvent event);
}
