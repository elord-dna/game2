package com.game_base.base;

import java.util.UUID;

import com.game_base.base.action.FightSkill;

public abstract class Skill implements FightSkill {

    private String id;
    private String name;
    private int level = 0;
    private SkillType skillType;
    private SkillScope skillScope;
    
    public Skill() {
        id = UUID.randomUUID().toString();
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        if(level < 1) level = 1;
        this.level = level;
    }
    public SkillType getSkillType() {
        return skillType;
    }
    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }
    public SkillScope getSkillScope() {
        return skillScope;
    }
    public void setSkillScope(SkillScope skillScope) {
        this.skillScope = skillScope;
    }
    @Override
    public String toString() {
        return String.format("%s[Lv%d]", name, level);
    }
}
