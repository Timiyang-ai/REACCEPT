public static User authenticateWithPlainPassword(String loginId, String password) {
        User user = User.findByLoginId(loginId);
        if (!user.isAnonymous()) {
            if (user.password.equals(hashedPassword(password,
                    user.passwordSalt))) {
                return user;
            }
        }
        return null;
    }