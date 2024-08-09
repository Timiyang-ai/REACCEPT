@Test
	public void testCheckUserId() {
		NGrinderBaseController ngridnerBaseController = new NGrinderBaseController();
		ModelMap model = new ModelMap();
		String rtnStr = userController.checkUserId(model, "not-exist");
		assertThat(rtnStr, is(ngridnerBaseController.returnSuccess()));

		rtnStr = userController.checkUserId(model, getTestUser().getUserId());
		assertThat(rtnStr, is(ngridnerBaseController.returnError()));
	}