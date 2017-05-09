package com.game_base.base;

/**
 * 表示战斗时的状态
 * @author Mr.H
 *
 */
public enum FightState {
    ALIVE,  
    DIED,
    WEAK,
    WAKEUP,
    SLEEPY,       // When Sleepy, role would not move forward, and would wake up when being attacked
    HURT,
    BLEEDING,   // When bleeding, atk lows down by 20% and def lows down by 50%
    TAIRED;
}
