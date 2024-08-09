@Override
	public void validate(DrugOrder order, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "dose", "DrugOrder.error.doseIsNullForDosingTypeSimple");
		ValidationUtils.rejectIfEmpty(errors, "doseUnits", "DrugOrder.error.doseUnitsIsNullForDosingTypeSimple");
		ValidationUtils.rejectIfEmpty(errors, "route", "DrugOrder.error.routeIsNullForDosingTypeSimple");
		ValidationUtils.rejectIfEmpty(errors, "frequency", "DrugOrder.error.frequencyIsNullForDosingTypeSimple");
		if (order.getAutoExpireDate() == null && order.getDurationUnits() != null) {
			if (ISO8601Duration.getCode(order.getDurationUnits()) == null) {
				errors.rejectValue("durationUnits", "DrugOrder.error.durationUnitsNotMappedToISO8601DurationCode");
			}
		}
	}