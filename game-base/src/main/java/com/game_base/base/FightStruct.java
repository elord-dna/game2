package com.game_base.base;
/**
 * 战斗属性
 * @author Mr.H
 *
 */
public class FightStruct {

    private int hp;
    private int mp;
    private int atk;
    private int def;
    private int speed;
    public FightStruct(int hp, int mp, int atk, int def, int speed) {
        this.hp = hp;
        this.mp = mp;
        this.atk = atk;
        this.def = def;
        this.speed = speed;
    }
    public FightStruct(FightRole fr) {
        this.hp = fr.getHp();
        atk = fr.getAtk();
        def = fr.getDef();
        speed = fr.getSpeed();
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getMp() {
        return mp;
    }
    public void setMp(int mp) {
        this.mp = mp;
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
}
