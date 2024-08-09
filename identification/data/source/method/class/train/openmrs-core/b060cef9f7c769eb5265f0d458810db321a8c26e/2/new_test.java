	@Test
	public void supports_shouldRejectClassesNotExtendingDrug() {
		Assert.assertFalse(new ConceptDrugValidator().supports(String.class));
	}