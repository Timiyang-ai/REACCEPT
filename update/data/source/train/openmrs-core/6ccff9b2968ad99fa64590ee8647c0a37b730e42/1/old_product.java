public void validate(Object obj, Errors errors) {
		Program p = (Program) obj;
		if (p == null) {
			errors.rejectValue("program", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			List<Program> programs = Context.getProgramWorkflowService().getAllPrograms(false);
			for (Program program : programs) {
				if (program.getName().equals(p.getName()) && !program.getProgramId().equals(p.getProgramId())) {
					errors.rejectValue("name", "general.error.nameAlreadyInUse");
					break;
				} else {
					Context.evictFromSession(program);
				}
			}
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "concept", "error.concept");
		}
	}