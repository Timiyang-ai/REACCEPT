@Test
	@Verifies(value = "should not retire location if reason is empty", method = "onSubmit(HttpServletRequest,HttpServletResponse,Object,BindException)")
	public void onSubmit_shouldNotRetireLocationIfReasonIsEmpty() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
		request.setParameter("locationId", "1");
		request.setParameter("retireReason", "");
		request.setParameter("retireLocation", "true");
		
		HttpServletResponse response = new MockHttpServletResponse();
		
		LocationFormController controller = (LocationFormController) applicationContext.getBean("locationForm");
		
		ModelAndView modelAndView = controller.handleRequest(request, response);
		
		// make sure an error is returned because of the empty retire reason
		BeanPropertyBindingResult bindingResult = (BeanPropertyBindingResult)modelAndView.getModel().get("org.springframework.validation.BindingResult.location");
		Assert.assertEquals("general.retiredReason.empty", bindingResult.getGlobalError().getDefaultMessage());
	}