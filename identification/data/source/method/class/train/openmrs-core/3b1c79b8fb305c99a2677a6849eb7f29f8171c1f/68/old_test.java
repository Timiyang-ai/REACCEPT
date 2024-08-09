	@Test(expected = APIException.class)
	public void getActiveEncounterVisitHandler_shouldThrowIfBeanWithGivenTypeAndNameNotFound() {
		
		String incorrectBeanName = OpenmrsConstants.REGISTERED_COMPONENT_NAME_PREFIX + "invalidName";
		
		GlobalProperty visitHandlerProperty = new GlobalProperty(OpenmrsConstants.GP_VISIT_ASSIGNMENT_HANDLER,
		        incorrectBeanName);
		
		Context.getAdministrationService().saveGlobalProperty(visitHandlerProperty);
		
		Context.getEncounterService().getActiveEncounterVisitHandler();
	}