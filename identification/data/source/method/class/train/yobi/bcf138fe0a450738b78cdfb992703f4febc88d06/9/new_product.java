public static boolean isManager(Long userId, Long projectId) {
        int findRowCount = find.where().eq("user.id", Role.MANAGER)
                .eq("project.id", projectId).findRowCount();
        return (findRowCount != 0) ? true : false;
    }