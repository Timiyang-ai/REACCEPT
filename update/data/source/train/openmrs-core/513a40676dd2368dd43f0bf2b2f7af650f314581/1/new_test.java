@Test
	public void saveOrder_shouldThrowAmbiguousOrderExceptionIfAnActiveDrugOrderForTheSameDrugFormulationExists()
	        throws Exception {
		executeDataSet("org/openmrs/api/include/OrderServiceTest-drugOrdersWithSameConceptAndDifferentFormAndStrength.xml");
		final Patient patient = patientService.getPatient(2);
		//sanity check that we have an active order for the same concept
		DrugOrder existingOrder = (DrugOrder) orderService.getOrder(1000);
		assertTrue(existingOrder.isActive());
		
		//New Drug order
		DrugOrder order = new DrugOrder();
		order.setPatient(patient);
		order.setDrug(existingOrder.getDrug());
		order.setEncounter(encounterService.getEncounter(6));
		order.setOrderer(providerService.getProvider(1));
		order.setCareSetting(existingOrder.getCareSetting());
		order.setDosingType(FreeTextDosingInstructions.class);
		order.setDosingInstructions("2 for 5 days");
		order.setQuantity(10.0);
		order.setQuantityUnits(conceptService.getConcept(51));
		order.setNumRefills(2);
		
		expectedException.expect(AmbiguousOrderException.class);
		expectedException
		        .expectMessage("Cannot have more than one active order for the same orderable and care setting at same time");
		orderService.saveOrder(order, null);
	}