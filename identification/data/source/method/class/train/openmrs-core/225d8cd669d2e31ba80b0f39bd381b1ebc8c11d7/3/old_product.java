public void validate(Object obj, Errors errors) {
		if (!ValidateUtil.isValidationOn()) {
			return;
		}
		
		PatientIdentifier pi = (PatientIdentifier) obj;
		try {
			validateIdentifier(pi);
		}
		catch (Exception e) {
			errors.reject(e.getMessage());
		}
	}