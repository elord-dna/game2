package com.game_base.stage;

import java.util.List;

import com.game_base.base.FightRole;

/**
 * 单例
 * @author Mr.H
 *
 */
public class StageManager {
    private static StageManager stageManager = null;
    private Stage stage;
    private StageChecker stageChecker;
    
    static {
        if (stageManager == null) {
            stageManager = new StageManager();
        }
    }
    
    public static StageManager getInstance() {
        return stageManager;
    }
    
    public Stage createStage(List<FightRole> teamA, List<FightRole> teamB) {
        if (stage == null) {
            stage = new Stage();
            stage.setTeamA(teamA);
            stage.setTeamB(teamB);
        } 
        return stage;
    }
    
    public Stage getStage() {
        if (stage != null) {
            return stage;
        } else {
            return null;
        }
    }
    
    public StageChecker getStageChecker() {
        if (stageChecker == null) {
            stageChecker = new PveChecker();
        }
        return stageChecker;
    }
}
