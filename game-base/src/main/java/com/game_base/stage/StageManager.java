package com.game_base.stage;

import java.util.List;

import com.game_base.base.FightRole;
import com.game_base.base.event.*;
import com.game_base.listener.AbstractStageEventListener;
import com.game_base.listener.TestEventListener;

/**
 * 单例
 * @author Mr.H
 *
 */
public class StageManager {
    private static StageManager stageManager = null;
    private Stage stage;
    private StageChecker stageChecker;
    private AbstractStageEventListener stageEventListener;
    private static EventManager eventManager;
    
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

    public AbstractStageEventListener getStageEventListener() {
        if (stageEventListener == null) {
            stageEventListener = new TestEventListener();
        }
        return stageEventListener;
    }

    public EventManager getEventManager() {
        if (eventManager == null) {
            eventManager = new EventManager();
        }
        return eventManager;
    }

    public void on(FightRole role, FightEvent event, Callback callback) {
        // TODO 通用的绑定时事件，而不是特定回调事件的绑定，也可以处理特定的回调事件
        // 是否暴露给外部扩展
        EventManager eventManager = getEventManager();
        eventManager.on(role, event, callback);
    }

    public void startRound(FightRole role) {
        EventContext eventContext = getEventManager().getEventContext();
        eventContext.setCurrentObj(role);
    }

    public void endRound(FightRole role) {
        EventContext eventContext = getEventManager().getEventContext();
        eventContext.clearAll();
    }
}
