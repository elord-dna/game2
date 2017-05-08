package com.game_base.base.skillimpl.once;

import com.game_base.base.FightRole;
import com.game_base.base.Skill;
import com.game_base.base.event.EventContext;
import com.game_base.base.event.EventManager;
import com.game_base.base.event.FightEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/5/2 0002.
 */
public class HeavyHit extends Skill {
    private static final Logger log = LogManager.getLogger(HeavyHit.class);
    private final int lvDmg = 5;
    public HeavyHit() {
        this(1);
    }
    public HeavyHit(int lv) {
        super();
        this.setName("HeavyHit");
        this.setLevel(lv);
    }
    @Override
    public void execute(FightRole user, FightRole aimed) {
        log.info("{}使用了{}", user.getName(), this.getName());
        EventManager eventManager = EventManager.getInstance();
        FightRole curRole = user;
        int atk = curRole.getFightStruct().getAtk();
        atk += this.getLevel() * lvDmg;
        curRole.getFightStruct().setAtk(atk);

        curRole.one(FightEvent.AFTERFIGHT, ()->{
            int atk2 = curRole.getFightStruct().getAtk();
            atk2 -= this.getLevel() * lvDmg;
            curRole.getFightStruct().setAtk(atk2);
        });
    }

    @Override
    public void init() {
        EventManager eventManager = EventManager.getInstance();
        EventContext eventContext = eventManager.getEventContext();
        FightRole curRole = eventContext.getCurrentObj();
        curRole.on(FightEvent.BEFOREFIGHT, ()->{
            double rd = new Random().nextDouble();
            if (rd < 0.3) {
                execute(curRole, null);
            }
        });
    }
}
