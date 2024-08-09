	@Test
	public void getValidIdentifier_shouldGetValidIdentifier() {

		//Make sure valid identifiers come back with the right check digit

		for (int i = 0; i < allowedIdentifiers.length; i++) {
			assertEquals(validator.getValidIdentifier(allowedIdentifiers[i]), allowedIdentifiers[i] + "-"
					+ allowedIdentifiersCheckDigits[i]);
		}
	}