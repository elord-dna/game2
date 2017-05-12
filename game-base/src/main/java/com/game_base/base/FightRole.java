package com.game_base.base;

import com.game_base.base.action.FightAction;
import com.game_base.base.event.Callback;
import com.game_base.base.event.FightEvent;
import com.game_base.base.event.IFightEvent;
import com.game_base.base.event.StageEvent;
import com.game_base.stage.StageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 战斗属性独立出来
 *
 * @author Mr.H
 */
public abstract class FightRole extends Role implements FightAction, IFightEvent {
    private static final Logger log = LogManager.getLogger(FightRole.class);

    private int maxHp = 1;
    private int hp = 0;
    private int atk = 0;
    private int def = 0;
    private int speed = 1;
    private Set<FightState> fightState = new HashSet<>();
    private List<Buff> buffList = new ArrayList<>();
    private FightStruct fightStruct;

    private StageManager stageManager = StageManager.getInstance();
    private List<Skill> skillList;

    protected FightRole() {
    }

    // TODO 这儿的初始化方法需要修正
    public FightRole(String name) {
        super(name);
        this.fightState.add(FightState.ALIVE);
        this.fightStruct = new FightStruct(this);
        skillList = new ArrayList<>();
    }

    public FightRole(FightRole fr) {
        this(fr.getName());
        this.maxHp = fr.maxHp;
        this.hp = fr.hp;
        this.atk = fr.atk;
        this.def = fr.def;
        this.speed = fr.speed;
        this.fightStruct = new FightStruct(this);
    }

    public boolean isAlive() {
        return fightState.contains(FightState.ALIVE);
    }

    protected void die() {
        fightState.clear();
        fightState.add(FightState.DIED);
        System.out.println(String.format("%s失去了战斗力", this.getName()));
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp <= maxHp * 0.3 && !this.getFightState().contains(FightState.HURT)) {
            // FIXME 不应该这么写，应该改为触发事件
            this.getFightState().add(FightState.HURT);
            getHurt(this, 1);
//            stageManager.getStageEventListener().onGetHurt(this, 1);
        } else if (hp <= 0) {
            hp = 0;
            die();
        }
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Set<FightState> getFightState() {
        return fightState;
    }

    public void setFightState(Set<FightState> fightState) {
        this.fightState = fightState;
    }

    public FightStruct getFightStruct() {
        return fightStruct;
    }

    public void setFightStruct(FightStruct fightStruct) {
        this.fightStruct = fightStruct;
    }

    public int getFightAtk() {
        return fightStruct.getAtk();
    }

    public void setFightAtk(int atk) {
        fightStruct.setAtk(atk);
    }

    public int getFightDef() {
        return fightStruct.getDef();
    }

    public void setFightDef(int def) {
        fightStruct.setDef(def);
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public List<Buff> getBuffList() {
        return buffList;
    }

    public void setBuffList(List<Buff> buffList) {
        this.buffList = buffList;
    }

    public void upAtk(int val) {
        setFightAtk(getFightAtk() + val);
        triggle(FightEvent.UPATK);
    }

    public void downAtk(int val) {
        setFightAtk(getFightAtk() - val);
        triggle(FightEvent.DOWNATK);
    }

    public void upAtk(double per) {
        int val = (int) (getAtk() * per);
        upAtk(val);
    }

    public void downAtk(double per) {
        int val = (int) (getAtk() * per);
        downAtk(val);
    }

    public void upDef(int val) {
        setFightDef(getFightDef() + val);
        triggle(FightEvent.UPDEF);
    }

    public void downDef(int val) {
        setFightDef(getFightDef() - val);
        triggle(FightEvent.DOWNDEF);
    }

    public void upDef(double per) {
        int val = (int) (def * per);
        upDef(val);
    }

    public void downDef(double per) {
        int val = (int) (def * per);
        downDef(val);
    }

    public int getFightSpeed() {
        return fightStruct.getSpeed();
    }

    public void setFightSpeed(int speed) {
        fightStruct.setSpeed(speed);
    }

    public void upSpeed(int val) {
        setFightSpeed(getFightSpeed() + val);
    }

    public void upSpeed(double per) {
        int val = (int) (speed * per);
        upSpeed(val);
    }

    public void downSpeed(int val) {
        setFightSpeed(getFightSpeed() + val);
    }

    public void downSpeed(double per) {
        int val = (int) (speed * per);
        downSpeed(val);
    }

    public void gainHealth(int val) {
        int origin = getHp();
        setHp(origin + val);
        triggle(FightEvent.GAINHEALTH);
    }
    public void loseHealth() {
        // TODO
        triggle(FightEvent.LOSEHHEALTH);
    }

    public void equipSkill(Skill skill) {
        // TODO
        // 当前角色切换
        toCurrentRole();
        // TODO judge if skill is permit
        skillList.add(skill);
        skill.init();
    }

    // 获取/（刷新）buff
    public void gainBuff(Buff buff) {
        int posi = isExsitBuff(buff);
        if (posi > -1) {
            // TODO combine
            freshBuff(posi, buff);
        } else {
            buffList.add(buff);
        }
    }

    // buff触发而减少层数甚至失去
    public void lackBuff(String name, FightRole from) {
        for (Buff b : buffList) {
            if (name.equals(b.getName())
                    && from.equals(b.getFrom())) {
                int rounds = b.getRounds();
                if (rounds < 1) {
                    // TODO throw exception
                    log.error("throw exception, rounds is {}", rounds);
                } else {
                    b.setRounds(rounds - 1);
                }
                break;
            }
        }

        clearBuff();
    }

    public int getBuffLevel(String name, FightRole from) {
        for (Buff b : buffList) {
            if (name.equals(b.getName())
                    && from.equals(b.getFrom())) {
                return b.getLevel();
            }
        }
        return 0;
    }

    public int getBuffRounds(String name, FightRole from) {
        for (Buff b : buffList) {
            if (name.equals(b.getName())
                    && from.equals(b.getFrom())) {
                return b.getRounds();
            }
        }
        return 0;
    }

    private void freshBuff(int posi, Buff buff) {
        Buff b = buffList.get(posi);
        int max = buff.getMaxLevel();
        if (max > b.getLevel()) {
            b.setLevel(b.getLevel() + 1);
        }

        b.setRounds(buff.getRounds());
    }

    public int isExsitBuff(Buff buff) {
        int posi = 0;
        for (Buff b : buffList) {
            if (buff.getName().equals(b.getName())
                    && buff.getFrom().equals(b.getFrom())
                    && buff.getType().equals(b.getType())) {
                return posi;
            }
            ++ posi;
        }
        return -1;
    }

    private void clearBuff() {
        buffList.removeIf(b->{
            return (b.getLevel() < 1 || b.getRounds() < 1);
        });
    }

    public void toCurrentRole() {
        stageManager.getEventManager().getEventContext().setCurrentObj(this);
    }

    @Override
    public void on(FightEvent event, Callback callback) {
        eventManager.on(this, event ,callback);
    }

    public void on(FightEvent event, Callback callback, int times) {
        eventManager.on(this, event, callback, times);
    }

    public void on(FightEvent event, Callback callback, FightRole from, String buffName) {
        eventManager.on(this, event, callback, from, buffName);
    }

    @Override
    public void one(FightEvent event, Callback callback) {
        eventManager.one(this, event, callback);
    }

    @Override
    public void triggle(FightEvent event) {
        eventManager.triggle(this, event);
    }

    @Override
    public void triggle(StageEvent event) {

    }

    @Override
    public void beDamaged(FightRole role, int val) {
        setHp(getHp() - val);
        stageManager.getStageChecker().diedChecker();
        stageManager.getStageChecker().endChecker();
    }

    @Override
    public String toString() {
        return "FightRole{" +
                "maxHp=" + maxHp +
                ", hp=" + hp +
                ", atk=" + atk +
                ", def=" + def +
                ", speed=" + speed +
                ", fightStruct=" + fightStruct +
                '}';
    }
}
