@Test
	public void testIsHashedEntry() {
		assertTrue(MD5_HASHED_ENTRY_0, PasswordHash.isHashedEntry(MD5_HASHED_ENTRY_0));
		assertTrue(MD5_HASHED_ENTRY_1, PasswordHash.isHashedEntry(MD5_HASHED_ENTRY_1));
		assertTrue(CMD5_HASHED_ENTRY_0, PasswordHash.isHashedEntry(CMD5_HASHED_ENTRY_0));
		assertTrue(CMD5_HASHED_ENTRY_1, PasswordHash.isHashedEntry(CMD5_HASHED_ENTRY_1));

		assertFalse(MD5_PASSWORD_1, PasswordHash.isHashedEntry(MD5_PASSWORD_1));
		assertFalse("topsecret", PasswordHash.isHashedEntry("topsecret"));
		assertFalse("top:secret", PasswordHash.isHashedEntry("top:secret"));
		assertFalse("secret Password", PasswordHash.isHashedEntry("secret Password"));
		assertFalse("Empty string", PasswordHash.isHashedEntry(""));
		assertFalse("Null", PasswordHash.isHashedEntry(null));
	}