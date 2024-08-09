public static User authenticateWithHashedPassword(String loginId, String password) {
        User user = User.findByLoginId(loginId);
        if (!user.isAnonymous()) {
            if (user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }