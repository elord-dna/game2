package com.game_base.base;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
public class Buff {
    private String name;
    private int rounds;
    private boolean isCoexist = false;  // 是否允许多个同名buff共存，不共存取最高值
    private int level = 1;                      // 叠加次数
    private int maxLevel = 1;
    private BuffType type;

    private FightRole from;   // buff来源，buff交由FightRole管理

    private static final int ALWAYS = -1;

    public Buff(String name, int rounds, boolean isCoexist, BuffType type, FightRole from, int level, int maxLevel) {
        this.name = name;
        this.rounds = rounds;
        this.isCoexist = isCoexist;
        this.type = type;
        this.from = from;
        this.level = level;
        this.maxLevel = maxLevel;
    }

    public Buff(String name, int rounds, BuffType type, FightRole from) {
        this(name, rounds, true, type, from, 1, 1);
    }

    public Buff(String name, int rounds, FightRole from) {
        this(name, rounds, BuffType.DAMAGE, from);
    }

    public Buff(String name, FightRole from) {
        this(name, ALWAYS, from);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public boolean isCoexist() {
        return isCoexist;
    }

    public void setCoexist(boolean coexist) {
        isCoexist = coexist;
    }

    public BuffType getType() {
        return type;
    }

    public void setType(BuffType type) {
        this.type = type;
    }

    public FightRole getFrom() {
        return from;
    }

    public void setFrom(FightRole from) {
        this.from = from;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }
}
