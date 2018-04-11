package com.game_base.base.buff;

import com.game_base.base.FightRole;

/**
 * v 0.3
 */
public class Buff {
    private Long id;        // buff's id, for saving in the db, 可以解决一些同名不同时问题
    private String name;   // buff's name
    private boolean isCoexist = false;  // 同名是否共存
    private int level = 1;      // 叠加次数
    private int maxLevel = 1; // 最大叠加次数
    private BuffType buffType;
    private FightRole from; // 来源，快照

    // v 0.3 版本加入，将很久不参与任何逻辑
    private String icon; // 图标，类型暂时用string表示，暂时不参与任何逻辑
}
