public static boolean isManager(Long projectId) {
        int findRowCount = find
                .where()
                    .eq("role.id", Role.MANAGER)
                    .eq("project.id", projectId)
                .findRowCount();
        return (findRowCount > 1) ? true : false;
    }