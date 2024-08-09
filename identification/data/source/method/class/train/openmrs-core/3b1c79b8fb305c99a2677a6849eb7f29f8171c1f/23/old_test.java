	@Test(expected = IllegalArgumentException.class)
	public void getProvidersByPerson_shouldFailIfPersonIsNull() {
		//given
		
		//when
		service.getProvidersByPerson(null);
		
		//then
		Assert.fail();
	}