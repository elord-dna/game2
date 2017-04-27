package com.game_base.listener;

import com.game_base.base.FightRole;
import com.game_base.stage.StageManager;

import java.util.Random;

/**
 * Created by Administrator on 2017/4/27 0027.
 */
public class TestEventListener extends AbstractStageEventListener {
    private StageManager stageManager = StageManager.getInstance();

    @Override
    public void onGainHealth(FightRole role, int val) {

    }

    @Override
    public void onLoseHealth(FightRole role, int val) {

    }

    @Override
    public void onCauseDamage(FightRole role, int val) {
        // TODO  stage.checkevent(role, event.name,val)
    }

    @Override
    public void onBeDamaged(FightRole role, int val) {

    }

    @Override
    public void onGetHurt(FightRole role, int val) {
        String words = role.getName() + "受了很严重的伤，";
        Random rd = new Random();
        double range = rd.nextDouble() + 0.5;
        if (range == 1) {
            range = 1.1;
        }
        if (range > 1) {
            words += "回光返照了。";
        } else {
            words += "变得虚弱了。";
        }
        role.setAtk((int) (role.getAtk() * range));
        System.out.println(words);
    }
}
