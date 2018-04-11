package com.game_base.base.skill;

import com.game_base.base.FightRole;

/**
 * v 0.3
 * 抽象技能，具体技能需要继承这个类
 */
public abstract class AbstractSkill implements ISkill {
    protected Long id;              // role 的 skill id
    protected Long skillId;          // skill 本身的 id
    protected String name;
    protected int level = 0;
    protected int stage = 0;
    protected SkillType skillType;

    protected FightRole user;       // 使用者

    public void setUser(FightRole role) {
        user = role;
    }

    @Override
    public String toString() {
        return String.format("[%d阶] %s[Lv.%d]", stage, name, level);
    }
}
