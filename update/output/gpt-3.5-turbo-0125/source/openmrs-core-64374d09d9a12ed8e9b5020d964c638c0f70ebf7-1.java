@Test
	public void saveVisitAttributeType_shouldCreateANewVisitAttributeType() throws Exception {
		executeDataSet(VISITS_ATTRIBUTES_XML);
		Assert.assertEquals(3, service.getAllVisitAttributeTypes().size());
		VisitAttributeType vat = new VisitAttributeType();
		vat.setName("Another one");
		vat.setDatatypeClassname("org.openmrs.customdatatype.datatype.FreeText");
		service.saveVisitAttributeType(vat);
		Assert.assertNotNull(vat.getId());
		Assert.assertEquals(4, service.getAllVisitAttributeTypes().size());
	}