protected String hashPassword(String password, PasswordType type) {
        switch (type) {
            case md5:
                return StringUtils.hash(password, "MD5");
            case sha1:
                return StringUtils.hash(password, "SHA-1");
            case sha256:
                return StringUtils.hash(password, "SHA-256");
            case sha512:
                return StringUtils.hash(password, "SHA-512");
            case bcrypt:
                String salt = bcryptCost > 0
                        ? BCrypt.gensalt(bcryptCost)
                        : BCrypt.gensalt();
                return BCrypt.hashpw(password, salt);
            case plain:
            default:
                return password;
        }
    }