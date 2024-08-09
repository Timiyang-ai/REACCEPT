	@Test
	public void isProviderIdentifierUnique_shouldReturnFalseIfTheIdentifierIsADuplicate() {
		executeDataSet(OTHERS_PROVIDERS_XML);
		Provider duplicateProvider = service.getProvider(200);
		
		Provider existingProviderToEdit = service.getProvider(1);
		existingProviderToEdit.setIdentifier(duplicateProvider.getIdentifier());
		Assert.assertFalse(service.isProviderIdentifierUnique(existingProviderToEdit));
	}