package com.game_base.base.event;

/**
 * Created by Administrator on 2017/5/4 0004.
 */
public interface OneCallback extends Callback {
    @Override
    void call();

    default void afterCall() {
        EventManager.getInstance().removeCallback(this);
    }
}
