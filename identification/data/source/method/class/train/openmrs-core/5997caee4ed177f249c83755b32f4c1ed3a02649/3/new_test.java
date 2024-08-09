	@Test
	public void equals_shouldReturnTrueOnTwoEmptyResults() {
		Assert.assertTrue(new EmptyResult().equals(new Result()));
	}