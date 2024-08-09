public static User authenticateWithPlainPassword(String loginId, String password) {
        User user = User.findByLoginId(loginId);
        if(user == User.anonymous) {
            return null;
        }
        return authenticate(user, hashedPassword(password, user.passwordSalt));
    }