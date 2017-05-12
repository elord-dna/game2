package com.game_base.base.event;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
public enum FightEvent {
    GAINHEALTH,
    LOSEHHEALTH,
    CAUSEDAMAGE,
    BEDAMAGED,
    GEHURT,

    UPATK,
    DOWNATK,
    UPDEF,
    DOWNDEF,

    // Stage Event --------------
    INIT,

    STARTROUND,
    ROUNDPREPARE,
    ROUNDREADY,

    BEFOREFIGHT,
    FIGHT,
    AFTERFIGHT,
    ATTACK,


    BEFOREEND,
    END,
    // -----------------------------
    ;
}
