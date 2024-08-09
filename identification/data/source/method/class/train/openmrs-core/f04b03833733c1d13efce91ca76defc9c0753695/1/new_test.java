	@Test
	public void setGlobalProperty_shouldCreateGlobalPropertyInDatabase() {
		String newKey = "new_gp_key";
		
		String initialValue = adminService.getGlobalProperty(newKey);
		assertNull(initialValue); // ensure gp doesn't exist before test
		adminService.setGlobalProperty(newKey, "new_key");
		
		String newValue = adminService.getGlobalProperty(newKey);
		assertNotNull(newValue);
	}