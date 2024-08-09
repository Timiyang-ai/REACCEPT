@Test
	public void saveOrder_shouldSetOrderTypeOfDrugOrderToDrugOrderIfNotSetAndConceptNotMapped() throws Exception {
		Drug drug = conceptService.getDrug(2);
		Concept unmappedConcept = conceptService.getConcept(113);
		
		Assert.assertNull(orderService.getOrderTypeByConcept(unmappedConcept));
		drug.setConcept(unmappedConcept);
		
		DrugOrder drugOrder = new DrugOrder();
		Encounter encounter = encounterService.getEncounter(3);
		drugOrder.setEncounter(encounter);
		drugOrder.setPatient(patientService.getPatient(7));
		drugOrder.setCareSetting(orderService.getCareSetting(1));
		drugOrder.setOrderer(Context.getProviderService().getProvider(1));
		drugOrder.setDateActivated(encounter.getEncounterDatetime());
		drugOrder.setDrug(drug);
		drugOrder.setDosingType(SimpleDosingInstructions.class);
		drugOrder.setDose(300.0);
		drugOrder.setDoseUnits(conceptService.getConcept(50));
		drugOrder.setQuantity(20.0);
		drugOrder.setQuantityUnits(conceptService.getConcept(51));
		drugOrder.setDuration(20.0);
		drugOrder.setDurationUnits(conceptService.getConcept(28));
		drugOrder.setFrequency(orderService.getOrderFrequency(3));
		drugOrder.setRoute(conceptService.getConcept(22));
		drugOrder.setNumRefills(10);
		drugOrder.setOrderType(null);
		
		orderService.saveOrder(drugOrder, null);
		Assert.assertNotNull(drugOrder.getOrderType());
		Assert.assertEquals(orderService.getOrderTypeByUuid(OrderType.DRUG_ORDER_TYPE_UUID), drugOrder.getOrderType());
	}