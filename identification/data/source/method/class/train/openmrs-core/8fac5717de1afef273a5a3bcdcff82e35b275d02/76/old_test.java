	@Test
	public void getPerson_shouldReturnNullWhenPersonNull() throws Exception {
		Assert.assertNull(Context.getPersonService().getPerson(null));
	}