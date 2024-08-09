    @Test
    public void hashPassword() throws Exception {
        assertTrue(MD5_PASSWORD.equals(jdbcAuthProvider.hashPassword(PASSWORD, JDBCAuthProvider.PasswordType.md5)));
        assertTrue(SHA1_PASSWORD.equals(jdbcAuthProvider.hashPassword(PASSWORD, JDBCAuthProvider.PasswordType.sha1)));
        assertTrue(SHA256_PASSWORD.equals(jdbcAuthProvider.hashPassword(PASSWORD, JDBCAuthProvider.PasswordType.sha256)));
        assertTrue(SHA512_PASSWORD.equals(jdbcAuthProvider.hashPassword(PASSWORD, JDBCAuthProvider.PasswordType.sha512)));
        assertFalse(BCRYPTED_PASSWORD.equals(jdbcAuthProvider.hashPassword(PASSWORD, JDBCAuthProvider.PasswordType.bcrypt)));
        assertTrue(OpenBSDBCrypt.checkPassword(BCRYPTED_PASSWORD, PASSWORD.toCharArray()));
    }