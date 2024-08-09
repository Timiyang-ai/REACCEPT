@Test
	@Verifies(value = "should not fail on field answers", method = "onSubmit(HttpServletRequest,HttpServletResponse,Object,BindException)")
	public void onSubmit_shouldNotFailOnFieldAnswers() throws Exception {
		final String FIELD_ID = "1";
		
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "");
		request.setParameter("fieldId", FIELD_ID);
		
		HttpServletResponse response = new MockHttpServletResponse();
		FieldFormController controller = (FieldFormController) applicationContext.getBean("fieldForm");
		controller.handleRequest(request, response);
		
		Context.closeSession();
		Context.openSession();
		authenticate();
		
		request = new MockHttpServletRequest("POST", "");
		response = new MockHttpServletResponse();
		request.setParameter("fieldId", FIELD_ID);
		request.setParameter("name", "Some concept");
		request.setParameter("description", "This is a test field");
		request.setParameter("fieldTypeId", "1");
		request.setParameter("name", "Some concept");
		request.setParameter("conceptId", "3");
		
		controller.handleRequest(request, response);
	}