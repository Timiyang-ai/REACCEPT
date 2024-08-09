public void validate(Object obj, Errors errors) {
		PatientIdentifier pi = (PatientIdentifier) obj;
		try {
			validateIdentifier(pi);
		}
		catch (Exception e) {
			errors.reject(e.getMessage());
		}
	}