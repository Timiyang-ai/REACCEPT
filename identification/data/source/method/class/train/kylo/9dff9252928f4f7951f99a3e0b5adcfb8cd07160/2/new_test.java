    private SecurityRole createRole(String sysName, String title, String descr, Action... perms) {
        SecurityRole role = this.provider.createRole(SecurityRole.FEED, sysName, title, descr);
        role.setPermissions(perms);
        return role;
    }