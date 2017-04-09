package com.game_base.stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game_base.base.FightRole;

public class PveChecker implements StageChecker {
    private StageManager stageManager = StageManager.getInstance();
    private Map<String, List<Object>> checkMap = new HashMap<>();
    @Override
    public Map<String, Object> initChecker() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> attackChecker() {
        Stage stage = stageManager.getStage();
        FightRole attacker = stage.getAttacker();
        List<Object> checkList = checkMap.get("attackList");
        if (checkList != null) {
            checkList.forEach(check -> {
                // TODO
            });
        }
        return null;
    }

    @Override
    public Map<String, Object> defChecker() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> diedChecker() {
        Stage stage = stageManager.getStage();
        List<FightRole> list = stage.getAliveList();
        List<FightRole> rlist = new ArrayList<>();
        if (!list.isEmpty()) {
            list.forEach(fr -> {
                if (!fr.isAlive()) {
                    rlist.add(fr);
                }
            });
            removeList(rlist);
        }
        return null;
    }
    
    protected void removeList(List<FightRole> rlist) {
        if (rlist.isEmpty()) {
            return;
        }
        Stage stage = stageManager.getStage();
        rlist.forEach(fr -> {
            stage.getAliveList().remove(fr);
            stage.getAliveTeamA().remove(fr);
            stage.getAliveTeamB().remove(fr);
        });
    }

    @Override
    public Map<String, Object> endChecker() {
        Stage stage = stageManager.getStage();
        if (stage.getAliveTeamA().isEmpty()) {
            System.out.println("Team B wins");
        } else if (stage.getAliveTeamB().isEmpty()) {
            System.out.println("Team A wins");
        }
        return null;
    }

}
