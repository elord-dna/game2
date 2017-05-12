package com.game_base.base.event;

import com.game_base.base.Buff;
import com.game_base.base.FightRole;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
public class CallAction implements Callback {
    private boolean isForever = false;  // 是否永久
    private int times = 1;  // 触发次数

    private String buffName;  // 需要根据buff的名称来判断了
    private FightRole role;         // 触发的角色
    private FightRole from;       // 来源角色
    private Callback callback;

    public CallAction(Callback callback, boolean isForever, FightRole role, FightRole from, String buffName) {
        this.callback = callback;
        this.isForever = isForever;
        this.role = role;
        this.from = from;
        this.buffName = buffName;
    }

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

    // 是否根据buff来判断执行次数
    public boolean isBuff() {
        if (role != null && buffName != null && from != null) {
            return true;
        }
        return false;
    }

    @Override
    public void call() {
        callback.call();
        if (!isForever) {
            if (isBuff()) {
                times = role.getBuffRounds(buffName, from);
                role.lackBuff(buffName, from);
            }
            --times;
        }
    }
}
