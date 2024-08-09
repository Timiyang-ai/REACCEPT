@Test
	public void testAuthenticateUser() {
		// test good path first
		Boolean answer = null;
		try {
			answer = nakamuraUserDirectoryProvider.authenticateUser(
					MOCK_PRINCIPAL, userEdit, "password not used");
		} catch (Throwable e) {
			fail("No exception should be thrown");
		}
		assertNotNull(answer);
		assertTrue(answer);
		verify(userEdit, times(1)).setEid(MOCK_PRINCIPAL);
		verify(userEdit, times(1)).setFirstName(MOCK_FIRSTNAME);
		verify(userEdit, times(1)).setLastName(MOCK_LASTNAME);
		verify(userEdit, times(1)).setEmail(MOCK_EMAIL);
	}