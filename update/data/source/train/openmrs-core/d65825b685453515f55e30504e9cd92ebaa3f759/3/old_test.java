@Test
	@Verifies(value = "set the user property forcePassword to false after successful password change", method = "handleSubmission()")
	public void handleSubmission_shouldChangeTheUserPropertyForcePasswordChangeToFalse() throws Exception {
		User user = Context.getAuthenticatedUser();
		new UserProperties(user.getUserProperties()).setSupposedToChangePassword(true);
		
		UserService us = Context.getUserService();
		us.saveUser(user, "Openmr5xy");
		
		ChangePasswordFormController controller = new ChangePasswordFormController();
		BindException errors = new BindException(controller.formBackingObject(), "user");
		
		controller.handleSubmission(new MockHttpSession(), "Passw0rd", "Passw0rd", "", "", "", Context
		        .getAuthenticatedUser(), errors);
		
		User modifiedUser = us.getUser(user.getId());
		assertTrue(!new UserProperties(modifiedUser.getUserProperties()).isSupposedToChangePassword());
	}