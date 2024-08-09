public static boolean isLoginId(String loginId) {
        int findRowCount = find.where().eq("loginId", loginId).findRowCount();
        return (findRowCount != 0);
    }