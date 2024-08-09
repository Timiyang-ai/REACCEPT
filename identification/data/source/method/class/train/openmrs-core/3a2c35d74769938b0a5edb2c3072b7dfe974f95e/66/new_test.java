	@Test
	public void isSecretAnswer_shouldReturnFalseWhenGivenAnswerDoesNotMatchTheStoredSecretAnswer() {
		User user = userService.getUser(502);
		Assert.assertFalse(userService.isSecretAnswer(user, "not the answer"));
	}