package com.game_base.stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game_base.base.FightRole;

/**
 * 舞台
 * @author Mr.H
 *
 */
public class Stage {
    
    private List<FightRole> teamA;
    private List<FightRole> teamB;
    
    private FightRole attacker;
    private FightRole defenser;
    
    private List<FightRole> aliveList;
    private List<FightRole> aliveTeamA;
    private List<FightRole> aliveTeamB;
    
    private Map<FightRole, Integer> pushCuv = new HashMap<>();
    public Stage() {}
    public Stage(Stage stage) {
        this.teamA = stage.teamA;
        this.teamB = stage.teamB;
    }
    public List<FightRole> getTeamA() {
        return teamA;
    }
    public void setTeamA(List<FightRole> teamA) {
        this.teamA = teamA;
    }
    public List<FightRole> getTeamB() {
        return teamB;
    }
    public void setTeamB(List<FightRole> teamB) {
        this.teamB = teamB;
    }
    public FightRole getAttacker() {
        return attacker;
    }
    public void setAttacker(FightRole attacker) {
        this.attacker = attacker;
    }
    public FightRole getDefenser() {
        return defenser;
    }
    public void setDefenser(FightRole defenser) {
        this.defenser = defenser;
    }
    
    public List<FightRole> getAliveList() {
        return aliveList;
    }
    public void setAliveList(List<FightRole> aliveList) {
        this.aliveList = aliveList;
    }
    public List<FightRole> getAliveTeamA() {
        return aliveTeamA;
    }
    public void setAliveTeamA(List<FightRole> aliveTeamA) {
        this.aliveTeamA = aliveTeamA;
    }
    public List<FightRole> getAliveTeamB() {
        return aliveTeamB;
    }
    public void setAliveTeamB(List<FightRole> aliveTeamB) {
        this.aliveTeamB = aliveTeamB;
    }
    public void dual() {
        initDual();
        dualing();
    }

    private void startRound(FightRole role) {
        StageManager.getInstance().startRound(role);
    }

    private void beforeEndRound(FightRole role) {
        StageManager.getInstance().beforeEndRound(role);
    }

    private void endRound(FightRole role) {
        // TODO role的回合结束
        StageManager.getInstance().startRound(role);
    }
    
    private void initDual() {
        aliveList = new ArrayList<>();
        aliveTeamA = new ArrayList<>(teamA);
        aliveTeamB = new ArrayList<>(teamB);
        aliveList.addAll(aliveTeamA);
        aliveList.addAll(aliveTeamB);
        System.err.println("初始化成功");
    }
    
    private void dualing() {
        while((!aliveTeamA.isEmpty()) && (!aliveTeamB.isEmpty())) {
            try {
                int push = 100;
                List<FightRole> actionList = getActionRole(push);
                if (!actionList.isEmpty()) {
                    for (FightRole a : actionList) {
                        // ADDM 判断a的状态是否继续进行
                        if (!a.isAlive()) {
                            continue;
                        }
                        startRound(a);
                        System.out.println(String.format("轮到%s的回合了", a.getName()));
                        if (aliveTeamA.contains(a)) {
                            if (aliveTeamB.isEmpty()) {
                                break;
                            }
                            a.attack(aliveTeamB.get(0));
                        } else {
                            if (aliveTeamA.isEmpty()) {
                                break;
                            }
                            a.attack(aliveTeamA.get(0));
                        }
                        Thread.sleep(800);
                        beforeEndRound(a);
                        // A的回合结束
                        endRound(a);
                    }
//                    actionList.forEach(a -> {
//                    });
                }
//                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private List<FightRole> getActionRole(int push) {
        List<FightRole> actionRole = new ArrayList<>();
        aliveList.forEach(alive -> {
            Integer cuv = pushCuv.get(alive);
            if (cuv == null) {
                cuv = 0;
            }
            cuv += alive.getSpeed();
            if (cuv >= push) {
                actionRole.add(alive);
                pushCuv.put(alive, 0);
            } else {
                pushCuv.put(alive, cuv);
            }
        });
        return actionRole;
    }
}
