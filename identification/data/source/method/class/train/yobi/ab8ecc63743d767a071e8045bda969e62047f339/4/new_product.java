public static User authenticateWithHashedPassword(String loginId, String password) {
        return authenticate(loginId, password, true);
    }