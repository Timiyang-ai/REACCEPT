	@Test
	public void getProvidersByPerson_shouldNotReturnRetiredProvidersIfIncludeRetiredFalse() {
		Collection<Provider> providers = service.getProvidersByPerson(personDao.getPerson(2), false);
		Assert.assertEquals(1, providers.size());
		Assert.assertFalse(providers.iterator().next().getRetired());
	}