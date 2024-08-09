public void validate(Object obj, Errors errors) {
		Program p = (Program) obj;
		if (p == null) {
			errors.rejectValue("program", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description.required");
			Program program = Context.getProgramWorkflowService().getProgramByName(p.getName());
			if (program != null && !program.equals(p)) {
				errors.rejectValue("name", "general.error.nameAlreadyInUse");
				
			} else {
				Context.evictFromSession(program);
			}
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "concept", "error.concept");
		}
	}