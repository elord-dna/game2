package com.game_base.base;

import java.util.Random;

/**
 * 角色的工厂类
 *
 */
public class PlayerFactory {

    public static FightRole createPlayer(String name) {
        FightRole fr = new Player(name); // 只是利用player的属性，而不是使用其特性
        randomFR(fr);
        return fr;
    }
    
    private static void randomFR(FightRole fr) {
        Random rd = new Random();
        fr.setAtk(rd.nextInt(10) + 20);
        fr.setDef(rd.nextInt(9) + 3);
        fr.setMaxHp(rd.nextInt(50) + 100);
        fr.setHp(fr.getMaxHp());
        fr.setLv(rd.nextInt(10) + 1);
        fr.setSpeed(rd.nextInt(5) + 3);
    }
    
    /**
     * 测试用随机生成
     * @return
     */
    protected static Player randomPlayer() {
        Player player = new Player();
        Random rd = new Random();
        player.setAtk(rd.nextInt(10) + 20);
        player.setDef(rd.nextInt(8) + 3);
        player.setName(System.currentTimeMillis() + "" + rd.nextInt(9));
//        player.setHP(rd.nextInt(20) * (rd.nextInt(2) + 1) + 100);
        return player;
    }
}
