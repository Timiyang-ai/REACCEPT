public static boolean isManager(Long projectId) {
        int findRowCount = find.where().eq("project.id", projectId)
                .eq("role.id", 1l).findRowCount();
        return (findRowCount > 1) ? true : false;
    }