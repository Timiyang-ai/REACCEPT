	@Test
	public void getGlobalPropertyObject_shouldReturnNullWhenNoGlobalPropertyMatchGivenPropertyName() {
		executeDataSet(ADMIN_INITIAL_DATA_XML);
		assertNull(adminService.getGlobalPropertyObject("magicResistSkill"));
	}