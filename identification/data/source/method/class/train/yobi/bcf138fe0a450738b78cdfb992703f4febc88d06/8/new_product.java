public static boolean isManager(Long projectId) {
        int findRowCount = Ebean.find(User.class).where()
                .eq("projectUser.project.id", projectId)
                .eq("projectUser.rolePermission.role.id", 1l).findSet().size();
        return (findRowCount > 1) ? true : false;
    }