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
                byte[] salt = new byte[16];
                new SecureRandom().nextBytes(salt);
                int cost = (bcryptCost < 4 || bcryptCost > 31) ? DEFAULT_BCRYPT_COST : bcryptCost;
                return OpenBSDBCrypt.generate(password.toCharArray(), salt, cost);
            case plain:
            default:
                return password;
        }
    }