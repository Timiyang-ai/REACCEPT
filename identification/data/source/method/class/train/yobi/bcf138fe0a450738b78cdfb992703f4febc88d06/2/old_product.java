public static boolean isManager(Long projectId) {
        int findRowCount = find
                .where()
                    .eq("role.id", Role.DEFAULT_MANAGER_ROLE)
                    .eq("project.id", projectId)
                .findRowCount();
        return (findRowCount > 1) ? true : false;
    }