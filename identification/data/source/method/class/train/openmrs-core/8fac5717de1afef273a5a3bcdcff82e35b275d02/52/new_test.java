	@Test
	public void getGlobalProperty_shouldNotFailWithNullPropertyName() {
		adminService.getGlobalProperty(null);
	}