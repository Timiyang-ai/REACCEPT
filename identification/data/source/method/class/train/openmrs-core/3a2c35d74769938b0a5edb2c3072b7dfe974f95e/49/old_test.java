	@Test
	public void changeQuestionAnswer_shouldMatchOnCorrectlyHashedStoredPassword() {
		executeDataSet(XML_FILENAME);
		Context.logout();
		Context.authenticate("correctlyhashedSha1", "test");

		userService.changeQuestionAnswer("test", "some question", "some answer");

		Context.logout(); // so that the next test reauthenticates
	}