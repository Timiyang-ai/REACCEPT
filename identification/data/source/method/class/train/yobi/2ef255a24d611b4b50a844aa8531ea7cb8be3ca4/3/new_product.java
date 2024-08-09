public static boolean isMember(Long userId, Long projectId) {
        if (userId == null) {
            return false;
        }
        int findRowCount = find.where().eq("user.id", userId).eq("project.id", projectId)
                .findRowCount();
        return (findRowCount != 0) ? true : false;
    }