package com.game_base.base.skill;

import com.game_base.base.Role;

/**
 * v 0.3
 * 将skill的接口挪到base中，skill下只是实现或者继承这些方法
 * 避免调用不到的问题
 */
public interface ISkill {
    void aff(Role role);
}
