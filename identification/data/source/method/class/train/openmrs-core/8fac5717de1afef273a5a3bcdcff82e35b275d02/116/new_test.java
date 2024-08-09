	@Test
	public void unvoidPerson_shouldReturnNullwhenGivenNull() {
		Assert.assertNull(Context.getPersonService().unvoidPerson(null));
	}