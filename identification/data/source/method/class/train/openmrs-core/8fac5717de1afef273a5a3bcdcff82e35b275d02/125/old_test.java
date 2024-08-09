	@Test
	public void voidPerson_shouldReturnNullwhenGivenNull() {
		Assert.assertEquals(Context.getPersonService().voidPerson(null, "Testing person null"), null);
		
	}