package com.game_base.test;

import java.util.ArrayList;
import java.util.List;


import com.game_base.base.FightRole;
import com.game_base.base.Player;
import com.game_base.base.PlayerFactory;
import com.game_base.base.skillimpl.once.HeavyHit;
import com.game_base.stage.Stage;
import com.game_base.stage.StageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContextFactory;

public class Test {
    private static final Logger log = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        List<FightRole> teamA = new ArrayList<>();
        List<FightRole> teamB = new ArrayList<>();
        Player pa1 = new Player(PlayerFactory.createPlayer("pa1"));
        Player pa2 = new Player(PlayerFactory.createPlayer("pa2"));
        Player pb1 = new Player(PlayerFactory.createPlayer("pb1"));
        Player pb2 = new Player(PlayerFactory.createPlayer("pb2"));
        pa1.equipSkill(new HeavyHit(1));
        teamA.add(pa1);
        log.info("pa1: {}", pa1.toString());
//        teamA.add(pa2);
        teamB.add(pb1);
        log.info("pb1: {}", pb1.toString());
//        teamB.add(pb2);
        StageManager sm = StageManager.getInstance();
        Stage stage = sm.createStage(teamA, teamB);
        stage.dual();
    }
}
