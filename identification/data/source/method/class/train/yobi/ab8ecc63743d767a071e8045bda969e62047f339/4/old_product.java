public static User authenticateWithHashedPassword(String loginId, String password) {
        User user = User.findByLoginId(loginId);
        return authenticate(user, password);
    }