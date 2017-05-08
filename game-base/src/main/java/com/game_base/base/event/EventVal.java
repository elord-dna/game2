package com.game_base.base.event;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
public class EventVal {
    private Map<ValueType, Number> valMap = new HashMap<>();

    public Number getVal(ValueType valueType) {
        return valMap.getOrDefault(valueType, 0);
    }

    public void setVal(ValueType valueType, Number val) {
        valMap.put(valueType, val);
    }

    public Number addVal(ValueType valueType, Number val) {
        Number origin = getVal(valueType);
        Number result = add(origin, val);
        setVal(valueType, result);
        return result;
    }

    private Number add(Number origin, Number val) {
        Number rt = null;
        if (origin instanceof Double) {
            rt = origin.doubleValue() + val.doubleValue();
        } else if (origin instanceof Integer) {
            rt = origin.intValue() + val.intValue();
        } else if (origin instanceof Long) {
            rt = origin.longValue() + val.longValue();
        } else if (origin instanceof Byte) {
            rt = origin.byteValue() + val.byteValue();
        } else if (origin instanceof Float) {
            rt = origin.floatValue() + val.floatValue();
        }
        return rt;
    }
}
