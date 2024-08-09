public void validate(Object obj, Errors errors) {
		Patient patient = (Patient) obj;
		checkIdentifiers(patient.getIdentifiers(), errors);
	}