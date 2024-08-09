	@Test
	public void getFieldAnswerByUuid_shouldReturnNullIfNoObjectFoundWithGivenUuid() {
		Assert.assertNull(Context.getFormService().getFieldAnswerByUuid("some invalid uuid"));
	}