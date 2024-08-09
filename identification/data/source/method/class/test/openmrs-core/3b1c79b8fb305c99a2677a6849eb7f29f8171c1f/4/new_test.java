	@Test
	public void getAllProviderAttributeTypes_shouldGetAllProviderAttributeTypesExcludingRetired() {
		List<ProviderAttributeType> types = service.getAllProviderAttributeTypes(false);
		assertEquals(2, types.size());
	}