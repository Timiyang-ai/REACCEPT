public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		
		DrugOrder order = (DrugOrder) obj;
		if (order == null) {
			errors.reject("error.general");
		} else {
			// for the following elements Order.hbm.xml says: not-null="true"
			ValidationUtils.rejectIfEmpty(errors, "asNeeded", "error.null");
			if (order.getAction() != Order.Action.DISCONTINUE) {
				ValidationUtils.rejectIfEmpty(errors, "dosingType", "error.null");
			}
			if (order.getDrug() != null)
				ValidationUtils.rejectIfEmpty(errors, "drug.concept", "error.null");
			
			if (order.getAction() != Order.Action.DISCONTINUE && order.getDosingType() != null) {
				if (order.getDosingType().equals(DrugOrder.DosingType.SIMPLE)) {
					ValidationUtils.rejectIfEmpty(errors, "dose", "DrugOrder.error.doseIsNullForDosingTypeSimple");
					ValidationUtils.rejectIfEmpty(errors, "doseUnits", "DrugOrder.error.doseUnitsIsNullForDosingTypeSimple");
					ValidationUtils.rejectIfEmpty(errors, "route", "DrugOrder.error.routeIsNullForDosingTypeSimple");
					ValidationUtils.rejectIfEmpty(errors, "frequency", "DrugOrder.error.frequencyIsNullForDosingTypeSimple");
				} else {
					ValidationUtils.rejectIfEmpty(errors, "dosingInstructions",
					    "DrugOrder.error.dosingInstructionsIsNullForDosingTypeOther");
				}
			}
			validateFieldsForOutpatientCareSettingType(order, errors);
			validatePairedFields(order, errors);
			validateUnitsAreAmongAllowedConcepts(errors, order);
		}
	}