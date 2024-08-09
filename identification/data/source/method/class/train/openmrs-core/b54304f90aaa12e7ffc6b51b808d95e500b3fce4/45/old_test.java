	@Test
	public void setValueBoolean_shouldNotSetValueForNonBooleanConcept() throws Exception {
		Obs obs = createObs(2);
		ConceptDatatype dataType = new ConceptDatatype();
		dataType.setUuid(ConceptDatatype.CODED_UUID);
		obs.getConcept().setDatatype(dataType);
		assertNotNull(obs.getValueCoded());
		obs.setValueBoolean(null);
		assertNotNull(obs.getValueCoded());
	}