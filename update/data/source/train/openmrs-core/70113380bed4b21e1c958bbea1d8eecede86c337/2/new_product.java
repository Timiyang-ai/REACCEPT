public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		
		DrugOrder order = (DrugOrder) obj;
		if (order == null) {
			errors.rejectValue("order", "error.general");
		} else {
			// for the following elements Order.hbm.xml says: not-null="true"
			ValidationUtils.rejectIfEmpty(errors, "asNeeded", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "dosingType", "error.null");
			
			if (order.getDrug() != null)
				ValidationUtils.rejectIfEmpty(errors, "drug.concept", "error.null");
			
			if (!(order.getConcept() == null)) {
				if (!(order.getDrug() == null) && !(order.getDrug().getConcept().equals(order.getConcept()))) {
					errors.rejectValue("drug", "error.general");
					errors.rejectValue("concept", "error.concept");
				}
			}
			if (order.getDosingType() != null) {
				if (order.getDosingType().equals(DrugOrder.DosingType.SIMPLE)) {
					ValidationUtils.rejectIfEmpty(errors, "dose", "error.doseIsNullForDosingTypeSimple");
					ValidationUtils.rejectIfEmpty(errors, "doseUnits", "error.doseUnitsIsNullForDosingTypeSimple");
					ValidationUtils.rejectIfEmpty(errors, "route", "error.routeIsNullForDosingTypeSimple");
					ValidationUtils.rejectIfEmpty(errors, "frequency", "error.frequencyIsNullForDosingTypeSimple");
				} else if (order.getDosingType().equals(DrugOrder.DosingType.FREE_TEXT)) {
					ValidationUtils.rejectIfEmpty(errors, "instructions", "error.instructionIsNullForDosingTypeFreeText");
				} else {
					ValidationUtils.rejectIfEmpty(errors, "dosingInstructions",
					    "error.dosingInstructionsIsNullForDosingTypeOther");
				}
			}
			validateFieldsForOutpatientCareSettingType(order, errors);
			validatePairedFields(order, errors);
		}
	}