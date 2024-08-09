@Test
    public void getDrugRoutes_shouldGetDrugRoutesAssociatedConceptPrividedInGlobalProperties() throws Exception {
        Concept drugRoutes = new Concept();
        ConceptName cn = new ConceptName("Test concept for drug routes", Locale.US);
        drugRoutes.addName(cn);
        drugRoutes.setSet(true);
        drugRoutes.addSetMember(conceptService.getConcept(3));
        drugRoutes.addSetMember(conceptService.getConcept(4));
        drugRoutes.addSetMember(conceptService.getConcept(5));
        drugRoutes=conceptService.saveConcept(drugRoutes);
        AdministrationService as = Context.getAdministrationService();
        List<GlobalProperty> globalProperties = Context.getAdministrationService().getAllGlobalProperties();
        globalProperties.add(new GlobalProperty(OpenmrsConstants.DRUG_ROUTE_CONCEPT_UUID, drugRoutes.getUuid(), "test"));
        as.saveGlobalProperties(globalProperties);
        List<Concept> drugRoutesList=orderService.getDrugRoutes();
        assertEquals(3,drugRoutesList.size());
    }