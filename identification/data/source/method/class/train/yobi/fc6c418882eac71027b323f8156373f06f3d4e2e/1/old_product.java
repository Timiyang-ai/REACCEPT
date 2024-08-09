public static boolean projectNameChangeable(Long id, String userName,
                                                String projectName) {
        int findRowCount = find.where().eq("name", projectName)
                .eq("owner", userName).ne("id", id).findRowCount();
        return (findRowCount == 0) ? true : false;
    }