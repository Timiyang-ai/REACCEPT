protected boolean comparePasswords(String plainText, String hashed) {
        int lastIndex = passwordTypes.size() - 1;
        if (passwordTypes.get(lastIndex) == PasswordType.bcrypt) {
            for (int i = 0; i < lastIndex; i++) {
                plainText = hashPassword(plainText, passwordTypes.get(i));
            }
            return OpenBSDBCrypt.checkPassword(hashed, plainText.toCharArray());
        }

        return hashPassword(plainText).equals(hashed);
    }