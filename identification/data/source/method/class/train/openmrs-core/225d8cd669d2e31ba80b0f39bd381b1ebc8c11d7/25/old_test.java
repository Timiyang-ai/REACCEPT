	@Test
	public void validate_shouldFailValidationIfConceptIsNullOrEmptyOrWhitespace() {
		ConceptStateConversion csc = new ConceptStateConversion();
		ProgramWorkflow workflow = Context.getProgramWorkflowService().getProgram(1).getAllWorkflows().iterator().next();
		csc.setProgramWorkflow(workflow);
		csc.setProgramWorkflowState(workflow.getState(1));
		
		Errors errors = new BindException(csc, "csc");
		new StateConversionValidator().validate(csc, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("concept"));
	}