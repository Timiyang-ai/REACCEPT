public static boolean projectNameChangeable(Long id, String userName,
                                                String projectName) {
        int findRowCount = find.where().ieq("name", projectName)
                .ieq("owner", userName).ne("id", id).findRowCount();
        return (findRowCount == 0);
    }