package com.game_base.base.action;

import java.util.List;
import java.util.Map;

import com.game_base.base.FightRole;

public interface FightSkill {
    /**
     * 执行技能
     * @param user
     * @param aimed
     * @return
     */
    public abstract Map<String, Object> execute(FightRole user, List<FightRole> aimed);
}
