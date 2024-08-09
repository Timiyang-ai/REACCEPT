@Transactional(readOnly = true)
	@Test
	@Verifies(value = "should get empty form with valid user", method = "formBackingObject(HttpServletRequest)")
	public void formBackingObject_shouldGetEmptyFormWithValidUser() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "");
		request.setParameter("userId", "1");
		
		HttpServletResponse response = new MockHttpServletResponse();
		
		UserFormController controller = (UserFormController) applicationContext.getBean("userForm");
		
		ModelAndView modelAndView = controller.handleRequest(request, response);
		
		// make sure there is a "userId" filled in on the concept
		User command = (User) modelAndView.getModel().get("user");
		Assert.assertNotNull(command.getUserId());
	}