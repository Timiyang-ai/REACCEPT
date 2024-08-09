	@Test
	public void areCompatible_shouldConfirmMatchingLanguageAsCompatible() {
		Locale lhs = Locale.ENGLISH;
		Locale rhs = Locale.ENGLISH;
		
		assertTrue(LocaleUtility.areCompatible(lhs, rhs));
	}