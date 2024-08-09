@Test(expected = IllegalArgumentException.class)
	public void getProvidersByPerson_shouldFailIfPersonIsNull() throws Exception {
		//given
		
		//when
		service.getProvidersByPerson(null);
		
		//then
		Assert.fail();
	}