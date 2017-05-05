package com.game_base.base.action;

import com.game_base.base.FightRole;

import java.util.List;

public interface FightSkill {
    /**
     * 执行技能
     * @param user
     * @param aimed
     * @return
     */
    void execute(FightRole user, List<FightRole> aimed);
}
