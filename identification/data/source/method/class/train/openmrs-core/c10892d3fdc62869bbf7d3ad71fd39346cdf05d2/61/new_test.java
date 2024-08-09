	@Test
	public void getLocationTagByUuid_shouldFindObjectGivenValidUuid() {
		Assert.assertEquals(Integer.valueOf(3), Context.getLocationService().getLocationTagByUuid(
		    "0d0eaea2-47ed-11df-bc8b-001e378eb67e").getLocationTagId());
	}