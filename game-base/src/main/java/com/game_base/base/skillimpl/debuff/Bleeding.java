package com.game_base.base.skillimpl.debuff;

import com.game_base.base.*;
import com.game_base.base.event.EventContext;
import com.game_base.base.event.EventManager;
import com.game_base.base.event.FightEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public class Bleeding extends Skill {
    private static final Logger log = LogManager.getLogger(Bleeding.class);
    private final double possi = 0.98 + 1;  // 0.25 possibility
    private final int dmgbase = 1;     // 伤害基数
    private final int times = 3;
    private final int maxLevel = 2;

    public Bleeding(int lv) {
        super();
        this.setName("Bleeding");
        this.setLevel(lv);
    }

    public Bleeding() {
        this(1);
    }

    @Override
    public void execute(FightRole user, FightRole aimed) {
        // check first
        boolean isBleeding = aimed.getFightState().contains(FightState.BLEEDING);
        Buff buff = new Buff("bleeding", times, true, BuffType.DAMAGE, user, 1, maxLevel);
        isBleeding = aimed.isExsitBuff(buff) > -1;
        if (isBleeding) {
            // IMPROVE 需要进一步的设定
            log.info("again");
            int lv = aimed.getBuffLevel(buff.getName(), buff.getFrom());
            if (lv < 3) {  // 测试能否无限正常刷新
                aimed.gainBuff(buff);
            }
            // update bleeding event
            return;
        } else {
            log.info("{}流血了", aimed.getName());
            aimed.getFightState().add(FightState.BLEEDING);
            aimed.gainBuff(buff);
        }
        aimed.on(FightEvent.BEFOREEND,() -> {
            int bufflevel = aimed.getBuffLevel("bleeding", user);
            int dmg = dmgbase * getLevel() * bufflevel;
            aimed.beDamaged(user, dmg);
            log.info("{}血管炸裂，流血不止，失去了{}HP", aimed.getName(), dmg);
        }, buff.getFrom(), buff.getName());
    }

    @Override
    public void init() {
        EventManager eventManager = EventManager.getInstance();
        EventContext eventContext = eventManager.getEventContext();
        FightRole curRole = eventContext.getCurrentObj();
        curRole.on(FightEvent.BEFOREFIGHT,() -> {
            FightRole aim = eventContext.getAimObj();
            aim.one(FightEvent.AFTERFIGHT, ()->{
                double rd = new Random().nextDouble();
                if (rd < possi) {
                    execute(curRole, aim);
                }
            });
        });

    }
}
