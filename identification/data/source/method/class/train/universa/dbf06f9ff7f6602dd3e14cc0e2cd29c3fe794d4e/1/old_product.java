@Override
    public boolean equals(Object obj) {
        if (obj instanceof RoleLink) {
            return ((RoleLink) obj).roleName.equals(roleName);
        }
        return false;
    }