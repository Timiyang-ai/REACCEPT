public void validate(Object obj, Errors errors) {
		if (!ValidateUtil.isValidationOn()) {
			return;
		}
		
		PersonMergeLog personMergeLog = (PersonMergeLog) obj;
		
		if (personMergeLog == null) {
			errors.rejectValue("persnMergeLog", "error.general");
		} else {
			ValidationUtils.rejectIfEmpty(errors, "personMergeLogData", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "winner", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "loser", "error.null");
		}
	}