@Test
    @Verifies(value = "should work for an example", method = "handleSubmission(WebRequest,HttpSession,String,String,String,null,User,BindingResult)")
    public void handleSubmission_shouldWorkForAnExample() throws Exception {
	    UserFormController controller = new UserFormController();
	    WebRequest request = new ServletWebRequest(new MockHttpServletRequest());
	    User user = controller.formBackingObject(request, null);
	    user.addName(new PersonName("This", "is", "Test"));
	    user.getPerson().setGender("F");
	    controller.handleSubmission(request, new MockHttpSession(), new ModelMap(), "Save User", "pass123", "pass123", new String[0], user, new BindException(user, "user"));
    }