	@Test
	public void getCareSettingByUuid_shouldReturnTheCareSettingWithTheSpecifiedUuid() {
		CareSetting cs = orderService.getCareSettingByUuid("6f0c9a92-6f24-11e3-af88-005056821db0");
		assertEquals(1, cs.getId().intValue());
	}