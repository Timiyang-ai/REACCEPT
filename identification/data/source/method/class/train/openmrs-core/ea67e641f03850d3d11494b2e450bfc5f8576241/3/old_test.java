	@Test
	public void isSuperUser_shouldReturnTrueIfGivenUserHasSuperuserRole() throws SQLException {
		Assert.assertTrue(new UpdateFilter().isSuperUser(getConnection(), 1));
	}