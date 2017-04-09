package com.game_base.base;
/**
 * 技能触发区
 * @author Mr.H
 *
 */
public enum SkillScope {
    INIT,            // 初始化触发
    BEFOREATTACK,    // 攻击前触发
    ATTACK,          // 攻击时触发
    AFTERATTACK,     // 攻击结束后触发
    BEAIMED,         // 被指定为目标时触发
    BEFOREDEF,       // 被攻击前触发
    DEF,             // 被攻击时触发
    AFTERDEF,        // 被攻击结束后触发
    BEFROEDIED,      // 死亡前触发
    DIED,            // 死亡时触发
    AFTERDIED,       // 死亡结束后触发
    REVIVED,         // 复活时触发
    BEFROEBATTLE,    // 战斗前触发，包括攻击前与被攻击前
    BATTLE,          // 战斗中触发，包括攻击时于被攻击时
    AFTERBATTLE,     // 战斗后触发，包括攻击后于被攻击后
    UNBATTLE,        // 与战斗无关的阶段触发
    ALL;             // 全程都检验触发
}
