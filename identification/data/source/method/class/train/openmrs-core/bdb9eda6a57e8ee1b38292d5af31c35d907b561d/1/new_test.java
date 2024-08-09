	@Test
	public void getDrugByMapping_shouldReturnADrugThatMatchesTheCodeAndSourceAndTheBestMapType() {
		executeDataSet(GET_DRUG_MAPPINGS);
		final Integer expectedDrugId = 2;
		final ConceptSource source = conceptService.getConceptSource(2);
		final ConceptMapType mapTypeWithMatch = conceptService.getConceptMapType(1);
		final ConceptMapType mapTypeWithNoMatch = conceptService.getConceptMapType(2);
		List<ConceptMapType> conceptMapTypeList = new ArrayList<>();
		conceptMapTypeList.add(mapTypeWithMatch);
		conceptMapTypeList.add(mapTypeWithNoMatch);
		Drug drug = conceptService.getDrugByMapping("WGT234", source, conceptMapTypeList);
		assertEquals(expectedDrugId, drug.getDrugId());
		
		//Lets switch the order is the map types in the list to make sure that
		//if there is no match on the first map type, the logic matches on the second
		//sanity check that actually there will be no match on the first map type in the list
		conceptMapTypeList.clear();
		conceptMapTypeList.add(mapTypeWithNoMatch);
		assertNull(conceptService.getDrugByMapping("WGT234", source, conceptMapTypeList));
		
		conceptMapTypeList.add(mapTypeWithMatch);
		drug = conceptService.getDrugByMapping("WGT234", source, conceptMapTypeList);
		assertEquals(expectedDrugId, drug.getDrugId());
	}