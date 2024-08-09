	@Test
	public void isSameAllergen_shouldReturnTrueForSameCodedAllergen() {
        Concept c = new Concept();
		Assert.assertTrue(new Allergen(null, c, null).isSameAllergen(new Allergen(null, c, null)));
	}