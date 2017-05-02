package com.game_base.base.skillimpl.once;

import com.game_base.base.FightRole;
import com.game_base.base.Skill;
import com.game_base.base.event.EventContext;
import com.game_base.base.event.EventManager;
import com.game_base.base.event.FightEvent;
import com.game_base.stage.StageManager;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/2 0002.
 */
public class HeavyHit extends Skill {
    public HeavyHit() {
        this(1);
    }
    public HeavyHit(int lv) {
        super();
        this.setName("HeavyHit");
    }
    @Override
    public void execute(FightRole user, List<FightRole> aimed) {
        EventManager eventManager = EventManager.getInstance();
        EventContext eventContext = eventManager.getEventContext();
        FightRole curRole = eventContext.getCauseObj(); // 这个方法封装到 EM 里
        eventManager.bindAction(curRole, FightEvent.BEFOREFIGHT, ()-> {
            // TODO callback
            int atk = curRole.getFightStruct().getAtk();
            atk += this.getLevel() * 3;
            curRole.getFightStruct().setAtk(atk);

            eventManager.one(curRole, FightEvent.AFTERFIGHT, ()->{
                int atk2 = curRole.getFightStruct().getAtk();
                atk2 -= this.getLevel() * 3;
                curRole.getFightStruct().setAtk(atk2);
            });
        });
    }

}
