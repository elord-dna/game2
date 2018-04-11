package com.game_skill.common;

import com.game_base.base.Role;
import com.game_base.base.skill.AbstractSkill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * v 0.3
 * 基础通用
 */
public class HeavyHit extends AbstractSkill {
    private static final Logger logger = LogManager.getLogger(HeavyHit.class);
    public HeavyHit() {
        this.name = "heavy hit";
        this.stage = 0;
    }
    @Override
    public void aff(Role role) {
        logger.info("{}使用了{} 勇猛地冲向了{}.", user.getName(), this.name, role.getName());
    }
}
