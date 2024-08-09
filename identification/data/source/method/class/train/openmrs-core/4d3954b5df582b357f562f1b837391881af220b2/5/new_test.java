	@Test
	public void put_shouldShouldIgnoreNonMatchingLocaleMessages() {
		
		presentationMessages.put(EXPECTED_MESSAGE_KEY, MESSAGE_EN);
		presentationMessages.put("wrong_locale", MESSAGE_DE);
		
		assertEquals(1, presentationMessages.size());
		assertTrue(presentationMessages.containsKey(EXPECTED_MESSAGE_KEY));
	}