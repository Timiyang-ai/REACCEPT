@Test
	public void getDrugRoutes_shouldGetDrugRoutesAssociatedConceptPrividedInGlobalProperties() throws Exception {
		Concept drugRoutes = new Concept();
		ConceptName cn = new ConceptName("Test concept for drug routes", Locale.US);
		drugRoutes.addName(cn);
		drugRoutes.setSet(true);
		Concept concept3 = conceptService.getConcept(3);
		Concept concept4 = conceptService.getConcept(4);
		Concept concept5 = conceptService.getConcept(5);
		drugRoutes.addSetMember(concept3);
		drugRoutes.addSetMember(concept4);
		drugRoutes.addSetMember(concept5);
		drugRoutes = conceptService.saveConcept(drugRoutes);
		
		AdministrationService as = Context.getAdministrationService();
		List<GlobalProperty> globalProperties = as.getAllGlobalProperties();
		globalProperties.add(new GlobalProperty(OpenmrsConstants.DRUG_ROUTE_CONCEPT_UUID, drugRoutes.getUuid(), "test"));
		as.saveGlobalProperties(globalProperties);
		List<Concept> drugRoutesList = orderService.getDrugRoutes();
		assertEquals(3, drugRoutesList.size());
		assertTrue(drugRoutesList.contains(concept3));
		assertTrue(drugRoutesList.contains(concept4));
		assertTrue(drugRoutesList.contains(concept5));
	}