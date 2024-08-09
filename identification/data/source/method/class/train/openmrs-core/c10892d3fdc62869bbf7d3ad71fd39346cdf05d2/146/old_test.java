	@Test
	public void saveGlobalProperties_shouldNotFailWithEmptyList() {
		adminService.saveGlobalProperties(new ArrayList<>());
	}