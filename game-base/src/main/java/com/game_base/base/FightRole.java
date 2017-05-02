package com.game_base.base;

import java.util.HashSet;
import java.util.Set;

import com.game_base.base.action.FightAction;
import com.game_base.base.event.IFightEvent;
import com.game_base.stage.StageManager;

/**
 * 战斗属性独立出来
 *
 * @author Mr.H
 */
public abstract class FightRole extends Role implements FightAction, IFightEvent {

    private int maxHp = 1;
    private int hp = 0;
    private int atk = 0;
    private int def = 0;
    private int speed = 1;
    private Set<FightState> fightState = new HashSet<>();
    private FightStruct fightStruct;

    private StageManager stageManager = StageManager.getInstance();

    protected FightRole() {
    }

    public FightRole(String name) {
        super(name);
        this.fightState.add(FightState.ALIVE);
        this.fightStruct = new FightStruct(this);
    }

    public FightRole(FightRole fr) {
        this(fr.getName());
        this.maxHp = fr.maxHp;
        this.hp = fr.hp;
        this.atk = fr.atk;
        this.def = fr.def;
        this.speed = fr.speed;
    }

    public boolean isAlive() {
        return fightState.contains(FightState.ALIVE);
    }

    protected void die() {
        fightState.clear();
        fightState.add(FightState.DIED);
        System.out.println(String.format("%s失去了战斗力", this.getName()));
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp <= maxHp * 0.3 && !this.getFightState().contains(FightState.HURT)) {
            // FIXME 不应该这么写，应该改为触发事件
            this.getFightState().add(FightState.HURT);
            getHurt(this, 1);
//            stageManager.getStageEventListener().onGetHurt(this, 1);
        } else if (hp <= 0) {
            hp = 0;
            die();
        }
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Set<FightState> getFightState() {
        return fightState;
    }

    public void setFightState(Set<FightState> fightState) {
        this.fightState = fightState;
    }

    public FightStruct getFightStruct() {
        return fightStruct;
    }

    public void setFightStruct(FightStruct fightStruct) {
        this.fightStruct = fightStruct;
    }

    public void gainHealth() {
        // TODO
    }
    public void loseHealth() {
        // TODO
    }

    private void equipSkill(Skill skill) {
        // TODO
    }

    @Override
    public String toString() {
        return "FightRole [hp=" + hp + ", atk=" + atk + ", def=" + def
                + ", speed=" + speed + "]";
    }

}
