public void validate(Object obj, Errors errors) {
		Program p = (Program) obj;
		if (p == null) {
			errors.rejectValue("program", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "concept", "error.concept");
			
			Program existingProgram = Context.getProgramWorkflowService().getProgramByName(p.getName());
			if (existingProgram != null && !existingProgram.getUuid().equals(p.getUuid())) {
				errors.rejectValue("name", "general.error.nameAlreadyInUse");
			}
			if (existingProgram != null && existingProgram.getUuid().equals(p.getUuid())) {
				Context.evictFromSession(existingProgram);
			}
			ValidateUtil.validateFieldLengths(errors, obj.getClass(), "name");
		}
	}