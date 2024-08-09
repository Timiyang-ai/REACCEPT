	@Test
	public void getCareSettingByName_shouldReturnTheCareSettingWithTheSpecifiedName() {
		CareSetting cs = orderService.getCareSettingByName("INPATIENT");
		assertEquals(2, cs.getId().intValue());
		
		//should also be case insensitive
		cs = orderService.getCareSettingByName("inpatient");
		assertEquals(2, cs.getId().intValue());
	}