public void validate(Object obj, Errors errors) {
		Patient patient = (Patient)obj;
		if (patient == null) {
			errors.rejectValue("patient", "error.general");
		}
		else {
			if (patient.getIdentifiers() == null || patient.getIdentifiers().size() < 1) {
				errors.rejectValue("identifiers", "error.identifiers");
			}
			if (patient.getNames() == null || patient.getNames().size() < 1) {
				errors.rejectValue("names", "error.name");
			}
		}
	}