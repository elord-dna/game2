package com.game_base.listener;

import com.game_base.base.Role;

/**
 * to handle the callback of every base role movement
 */
public interface IRoleListener extends IEventListener {
    // when talk with other role, or even self
    default void onTalk(Role role) {};
    // when damaged by other roles
    default void onDamaged(Role role) {};
    // when healed
    default void onHealed(Role role) {};
    // when scared
    default void onScared(Role role) {};
    // when caught
    default void onCaught(Role role) {};
    // when stun
    default void onStun(Role role) {};
    // when dizzied
    default void onDizzied(Role role) {};
    // when charmed
    default void onCharmed(Role role) {};
    // when fired
    default void onFired() {};
    // when sleepy
    default void onSleepy() {};
}
