	@Test
	public void getPersonAddressByUuid_shouldFindObjectGivenValidUuid() throws Exception {
		String uuid = "3350d0b5-821c-4e5e-ad1d-a9bce331e118";
		PersonAddress personAddress = Context.getPersonService().getPersonAddressByUuid(uuid);
		Assert.assertEquals(2, (int) personAddress.getPersonAddressId());
	}