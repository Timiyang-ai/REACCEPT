public void validate(Object obj, Errors errors) {
		PatientIdentifier pi = (PatientIdentifier) obj;
		try {
			validateIdentifier(pi);
			ValidateUtil.validateFieldLengths(errors, obj.getClass(), "identifier", "voidReason");
		}
		catch (Exception e) {
			errors.reject(e.getMessage());
		}
	}