	@Test
	public void containsRole_shouldBeCaseInsensitive() {
		user.addRole(new Role(MATERNITY_NURSE_UPPERCASE));
		assertTrue(user.containsRole(MATERNITY_NURSE_UPPERCASE));
		assertTrue(user.containsRole(MATERNITY_NURSE_LOWERCASE));
	}