package com.hzl.game_skill.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game_base.base.FightRole;
import com.game_base.base.Skill;
import com.game_base.base.SkillScope;
import com.game_base.base.SkillType;

public class HeavyHit extends Skill {

    private HeavyHit() {
        super();
        this.setName(this.getClass().getSimpleName());
        this.setSkillType(SkillType.PASSIVE);
        this.setSkillScope(SkillScope.ATTACK);
    }
    private HeavyHit(int level) {
        this();
        this.setLevel(level);
    }
    @Override
    public void execute(FightRole user, FightRole aimed) {
        Map<String, Object> map = new HashMap<>();
    }

    @Override
    public void init() {

    }
}
