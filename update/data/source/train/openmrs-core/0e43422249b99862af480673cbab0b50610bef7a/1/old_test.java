@Ignore
	@Test
	@Verifies(value = "set empty string properties to null", method = "handle(OpenmrsObject,User,Date,String)")
	public void handle_shouldSetEmptyStringPropertiesToNull() {
		Role role = new Role();
		role.setName("");
		role.setDescription(" ");
		role.setRole("");
		
		new OpenmrsObjectSaveHandler().handle(role, null, null, null);
		
		Assert.assertNull(role.getName());
		Assert.assertNull(role.getDescription());
		Assert.assertNull(role.getRole());
	}