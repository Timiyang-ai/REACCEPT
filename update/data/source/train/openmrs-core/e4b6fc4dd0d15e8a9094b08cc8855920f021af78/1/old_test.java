@Test
	@Verifies(value = "should add a new Concept map when creating a concept", method = "onSubmit(HttpServletRequest,HttpServletResponse,Object,BindException)")
	public void onSubmit_shouldAddANewConceptMapWhenCreatingAConcept() throws Exception {
		ConceptService cs = Context.getConceptService();
		final String conceptName = "new concept";
		// make sure the concept doesn't already exist
		Concept newConcept = cs.getConceptByName(conceptName);
		assertNull(newConcept);
		
		ConceptFormController conceptFormController = (ConceptFormController) applicationContext.getBean("conceptForm");
		
		MockHttpServletRequest mockRequest = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		mockRequest.setMethod("POST");
		mockRequest.setParameter("action", "");
		mockRequest.setParameter("namesByLocale[en].name", conceptName);
		mockRequest.setParameter("concept.datatype", "1");
		mockRequest.setParameter("conceptMappings[0].conceptReferenceTerm", "1");
		mockRequest.setParameter("conceptMappings[0].conceptMapType", "3");
		
		ModelAndView mav = conceptFormController.handleRequest(mockRequest, response);
		assertNotNull(mav);
		assertTrue(mav.getModel().isEmpty());
		
		Concept createdConcept = cs.getConceptByName(conceptName);
		assertNotNull(createdConcept);
		Assert.assertEquals(1, createdConcept.getConceptMappings().size());
	}