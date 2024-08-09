public void validate(Object obj, Errors errors) {
		if (!ValidateUtil.isValidationOn()) {
			return;
		}
		
		ConceptStateConversion c = (ConceptStateConversion) obj;
		if (c == null) {
			log.debug("Rejecting because c is null");
			errors.rejectValue("conceptStateConversion", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "concept", "error.concept");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "programWorkflow", "error.programWorkflow");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "programWorkflowState", "error.programWorkflowState");
		}
	}