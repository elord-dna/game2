package com.game_base.base.skillimpl.buff;

import com.game_base.base.FightRole;
import com.game_base.base.Skill;
import com.game_base.base.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
public class HealthDrink extends Skill {
    private static final Logger log = LogManager.getLogger(HealthDrink.class);
    private final double percent = 0.05;
    public HealthDrink(int lv) {
        super();
        setName("HealthDrink");
        setLevel(lv);
    }
    public HealthDrink() {
        this(1);
    }
    @Override
    public void execute(FightRole user, FightRole aimed) {
        Integer dmg = EventManager.getInstance().getEventContext().getVal(FromTos.getFromTo(user, aimed), ValueType.DAMAGE);
        int hd = (int) (dmg * percent * this.getLevel());
        user.gainHealth(hd);
        log.info("{}发动了{}，吸取了生命值{}", user.getName(), this.getName(), hd);
    }

    @Override
    public void init() {
        EventManager eventManager = EventManager.getInstance();
        EventContext eventContext = eventManager.getEventContext();
        FightRole curRole = eventContext.getCurrentObj();
        curRole.on(FightEvent.AFTERFIGHT, ()->{
            FightRole aim = EventManager.getInstance().getEventContext().getAimObj();
            execute(curRole, aim);
        });
    }
}
