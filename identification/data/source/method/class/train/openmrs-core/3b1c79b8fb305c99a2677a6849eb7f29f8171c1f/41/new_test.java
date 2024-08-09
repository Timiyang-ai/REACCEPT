	@Test
	public void getGlobalPropertyByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "4f55827e-26fe-102b-80cb-0017a47871b3";
		GlobalProperty prop = adminService.getGlobalPropertyByUuid(uuid);
		assertEquals("locale.allowed.list", prop.getProperty());
	}