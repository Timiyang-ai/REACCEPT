public static User authenticateWithPlainPassword(String loginId, String password) {
        return authenticate(loginId, password, false);
    }