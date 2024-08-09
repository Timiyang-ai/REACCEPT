	@Test
	public void getValueAsString_shouldReturnNonPreciseValuesForNumericConcepts() throws Exception {
		Obs obs = new Obs();
		obs.setValueNumeric(25.125);
		ConceptNumeric cn = new ConceptNumeric();
		ConceptDatatype cdt = new ConceptDatatype();
		cdt.setHl7Abbreviation("NM");
		cn.setDatatype(cdt);
		cn.setAllowDecimal(false);
		obs.setConcept(cn);
		String str = "25";
		Assert.assertEquals(str, obs.getValueAsString(Locale.US));
	}