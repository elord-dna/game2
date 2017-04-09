package com.game_base.base;

import java.util.UUID;

public abstract class Role {

    private String id;
    private String name;
    private RoleType roleType = RoleType.DEFAULT;
    private int lv = 0;
    public Role() {
    }
    protected Role(String id, String name) {
        this.id = id;
        this.name = name;
    }
    protected Role(String id, String name, RoleType roleType) {
        this.id = id;
        this.name = name;
        this.roleType = roleType;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
    public Role(String name, RoleType roleType) {
        this.id = createId();
        this.name = name;
        this.roleType = roleType;
    }
    public Role(String name) {
        this(name, RoleType.DEFAULT);
    }
    protected String createId() {
        String id = UUID.randomUUID().toString();
        return id;
    }
    public String getName() {
        return name;
    }
    public RoleType getRoleType() {
        return roleType;
    }
    public int getLv() {
        return lv;
    }
    public void setLv(int lv) {
        this.lv = lv;
    }
}
