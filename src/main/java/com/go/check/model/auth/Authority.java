package com.go.check.model.auth;

public enum Authority {
    ADMIN,
    OPERATOR,
    MEMBER,
    GUEST;

    public boolean isAdministrative() {
        return isAdministrative(this);
    }

    public boolean isOperative() {
        return isOperative(this);
    }

    public boolean isMember() {
        return isMember(this);
    }
    public static boolean isAdministrative(Authority authority) {
        if (authority == null)
            return false;
        return authority.equals(ADMIN);
    }

    public static boolean isOperative(Authority authority) {
        if (authority == null)
            return false;
        switch (authority) {
            case ADMIN:
            case OPERATOR:
                return true;
            default:
                return false;
        }
    }

    public static boolean isMember(Authority authority) {
        if (authority == null)
            return false;
        switch (authority) {
            case ADMIN:
            case OPERATOR:
            case MEMBER:
                return true;
            default:
                return false;
        }
    }
}
