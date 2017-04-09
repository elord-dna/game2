package com.game_base.base.action;

import java.util.List;
import java.util.Map;

import com.game_base.base.FightRole;
import com.game_base.base.Skill;

public interface FightAction {

    Map<String, Object> attack(List<FightRole> roles);
    
    Map<String, Object> attack(List<FightRole> roles, Skill skill);
    
    Map<String, Object> attack(FightRole r);
    
    Map<String, Object> attack(FightRole r, Skill skill);
}
