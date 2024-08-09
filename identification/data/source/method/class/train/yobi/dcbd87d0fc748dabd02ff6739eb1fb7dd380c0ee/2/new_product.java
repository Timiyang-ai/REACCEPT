public static boolean isLoginIdExist(String loginId) {
        int findRowCount = find.where().eq("loginId", loginId).findRowCount();
        return (findRowCount != 0);
    }