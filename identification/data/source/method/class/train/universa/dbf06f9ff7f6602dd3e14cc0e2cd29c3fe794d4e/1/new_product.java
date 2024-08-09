@Override
    protected boolean equalsIgnoreNameAndRefs(Role otherRole) {
        if (otherRole instanceof RoleLink) {
            return ((RoleLink) otherRole).roleName.equals(roleName);
        }
        return false;
    }