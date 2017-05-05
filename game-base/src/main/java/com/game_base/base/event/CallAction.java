package com.game_base.base.event;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
public class CallAction implements Callback {
    private boolean isForever = false;  // 是否永久
    private int times = 1;  // 触发次数
    private Callback callback;

    public CallAction(Callback callback, int times, boolean isForever) {
        this.callback = callback;
        this.times = times;
        this.isForever = isForever;
    }

    public CallAction(Callback callback, int times) {
        this(callback, times, false);
    }

    public  CallAction(Callback callback) {
        this(callback, 1, true);
    }

    // 检查是否有效
    public boolean isEffective() {
        return times > 0;
    }

    @Override
    public void call() {
        callback.call();
        if (!isForever) {
            --times;
        }
    }
}
