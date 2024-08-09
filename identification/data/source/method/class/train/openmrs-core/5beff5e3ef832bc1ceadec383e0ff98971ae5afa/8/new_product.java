@Override
	public void validate(Object target, Errors errors) {
		Relationship relationship = (Relationship) target;
		
		if (relationship != null) {
			Date startDate = relationship.getStartDate();
			Date endDate = relationship.getEndDate();
			if (startDate != null && endDate != null) {
				if (startDate.after(endDate)) {
					errors.reject("Relationship.InvalidEndDate.error");
				}
			}
			ValidateUtil.validateFieldLengths(errors, target.getClass(), "voidReason");
		}
		
	}