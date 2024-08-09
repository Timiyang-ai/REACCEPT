public static User authenticateWithPlainPassword(String loginId, String password) {
        User user = User.findByLoginId(loginId);
        return authenticate(user, hashedPassword(password, user.passwordSalt));
    }