public void validate(Object obj, Errors errors) {
		Patient patient = (Patient)obj;
		if (patient == null) {
			errors.rejectValue("patient", "general.error");
		}
		else {
			if (patient.getIdentifiers() == null || patient.getIdentifiers().size() < 1) {
				errors.rejectValue("identifiers", "patient.identifiers.error");
			}
			if (patient.getNames() == null || patient.getNames().size() < 1) {
				errors.rejectValue("names", "Patient.name.error");
			}
		}
	}