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
			if (order.getDrug() == null || order.getDrug().getConcept() == null) {
				ValidationUtils.rejectIfEmpty(errors, "concept", "error.null");
			}
			
			if (order.getConcept() != null && order.getDrug() != null && order.getDrug().getConcept() != null
			        && !order.getDrug().getConcept().equals(order.getConcept())) {
				errors.rejectValue("drug", "error.general");
				errors.rejectValue("concept", "error.concept");
			}
			if (order.getAction() != Order.Action.DISCONTINUE && order.getDosingType() != null) {
				DosingInstructions dosingInstructions = order.getDosingInstructionsInstance();
				dosingInstructions.validate(order, errors);
			}
			validateFieldsForOutpatientCareSettingType(order, errors);
			validatePairedFields(order, errors);
			validateUnitsAreAmongAllowedConcepts(errors, order);
			validateForRequireDrug(errors);
			ValidateUtil.validateFieldLengths(errors, obj.getClass(), "asNeededCondition", "brandName");
		}
	}