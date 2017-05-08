package com.game_base.base.event;

import com.game_base.base.FightRole;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
public class FromTos {
    private static Map<FightRole, Map<FightRole, FromTo>> pool = new HashMap<>();
    public static FromTo getFromTo(FightRole from, FightRole to) {
        FromTo rt = null;
        if (pool.containsKey(from)) {
            Map<FightRole, FromTo> fromMap = pool.get(from);
            if (fromMap.containsKey(to)) {
                rt = fromMap.get(to);
            }
        }
        return rt;
    }

}
