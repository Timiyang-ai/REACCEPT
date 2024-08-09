	@Test
	public void getHydratedObject_shouldLoadClassInFormatProperty() {
		PersonAttributeType type = new PersonAttributeType();
		type.setFormat("org.openmrs.Concept");
		
		PersonAttribute pa = new PersonAttribute(2);
		pa.setAttributeType(type);
		pa.setValue("5089");
		
		Concept concept = (Concept) pa.getHydratedObject();
		Assert.assertEquals(5089, concept.getConceptId().intValue());
	}