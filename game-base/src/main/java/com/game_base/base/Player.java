package com.game_base.base;

import java.util.List;
import java.util.Map;

import com.game_base.base.action.FightAction;
import com.game_base.stage.StageManager;

public class Player extends FightRole implements FightAction {
    private StageManager stageManager = StageManager.getInstance();
    
    public Player() {}
    public Player(String name) {
        super(name);
    }
    public Player(FightRole fr) {
        super(fr);
    }

    private void normalAttack(FightRole r) {
        if (!(r instanceof Player)) {
            return;
        }
        System.out.println(String.format("%s攻击了%s: ", this.getName(), r.getName()));
        stageManager.getStageChecker().attackChecker();
        Player p = (Player) r;
        int dmg = countDamage(this, r);
        int leftHP = p.getHp() - dmg;
        leftHP = leftHP<0? 0 : leftHP;
        System.out.println(String.format("%s对%s造成了%d点伤害，%s剩余%dHP", this.getName(), r.getName(), dmg, r.getName(), leftHP));
        p.setHp(leftHP);
    }
    protected int countDamage(FightRole r1, FightRole r2) {
        // 计算伤害的新方法
        // 防御因子
        double k1 = (6 * r2.getDef()) / ((90 + 10 * r2.getLv()) + 6 * r2.getDef());
        // 等级因子
        int dlv = r2.getLv() - r1.getLv();
        double k2 = 0;
        if (dlv >= 0) {
            k2 = 10 * dlv / (100 + 6 * dlv);
        } else {
            k2 = 10 * dlv / (100 - 2 * dlv);
        }
        // damage
        int dmg = (int) (r1.getAtk() * (1-k1) * (1-k2));
        return dmg;
    }

    @Override
    public Map<String, Object> attack(List<FightRole> roles) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> attack(List<FightRole> roles, Skill skill) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> attack(FightRole r) {
        normalAttack(r);
        stageManager.getStageChecker().diedChecker();
        stageManager.getStageChecker().endChecker();
        return null;
    }

    @Override
    public Map<String, Object> attack(FightRole r, Skill skill) {
        // TODO Auto-generated method stub
        return null;
    }

}
