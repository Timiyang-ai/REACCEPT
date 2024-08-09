@Test
	public void testCheckDuplicatedID() {
		NGrinderBaseController ngrinderBaseController = new NGrinderBaseController();
		ModelMap model = new ModelMap();
		HttpEntity<String> rtnStr = userController.checkDuplication(model, "not-exist");
		assertThat(rtnStr.getBody(), is(ngrinderBaseController.returnSuccess()));

		rtnStr = userController.checkDuplication(model, getTestUser().getUserId());
		assertThat(rtnStr.getBody(), is(ngrinderBaseController.returnError()));
	}