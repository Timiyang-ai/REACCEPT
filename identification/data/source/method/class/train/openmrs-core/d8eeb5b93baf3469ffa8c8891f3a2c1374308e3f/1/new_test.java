	@Test
	public void isValid_shouldFailWithInvalidIdentifiers() {
		//Make sure invalid identifiers throw an exception
		for (String invalidIdentifier: invalidIdentifiers) {
			try {
				validator.isValid(invalidIdentifier);
				fail("Identifier " + invalidIdentifier + " should have failed.");
			} catch (Exception e) { /* Expected */ }
		}
	}