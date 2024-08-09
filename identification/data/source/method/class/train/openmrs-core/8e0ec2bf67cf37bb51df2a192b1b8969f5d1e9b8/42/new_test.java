	@Test
	public void getAnswers_shouldNotReturnNullIfAnswersListIsNull() {
		Concept c = new Concept();
		c.setAnswers(null);
		Assert.assertNotNull(c.getAnswers());
		c.setAnswers(null);
		Assert.assertNotNull(c.getAnswers(true));
	}