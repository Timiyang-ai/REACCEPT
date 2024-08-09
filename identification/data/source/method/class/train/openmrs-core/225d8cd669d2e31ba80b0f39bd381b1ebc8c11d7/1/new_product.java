@Override
	public void validate(Object obj, Errors errors) {
		
		PersonMergeLog personMergeLog = (PersonMergeLog) obj;
		
		if (personMergeLog == null) {
			errors.rejectValue("persnMergeLog", "error.general");
		} else {
			ValidationUtils.rejectIfEmpty(errors, "personMergeLogData", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "winner", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "loser", "error.null");
			ValidateUtil.validateFieldLengths(errors, obj.getClass(), "voidReason");
		}
	}