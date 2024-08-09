public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		
		DrugOrder order = (DrugOrder) obj;
		if (order == null) {
			errors.rejectValue("order", "error.general");
		} else {
			ValidationUtils.rejectIfEmpty(errors, "concept", "error.null");
			if (order.getDuration() != null) {
				ValidationUtils.rejectIfEmpty(errors, "durationUnits", "DrugOrder.add.error.missingDurationUnits");
			}
			
			if (order.getQuantity() != null) {
				ValidationUtils.rejectIfEmpty(errors, "quantityUnits", "DrugOrder.add.error.missingQuantityUnits");
			}
			
			if (order.getStrength() != null) {
				ValidationUtils.rejectIfEmpty(errors, "strengthUnits", "DrugOrder.add.error.missingStrengthUnits");
			}
			
			if (order.getDose() != null) {
				ValidationUtils.rejectIfEmpty(errors, "doseUnits", "DrugOrder.add.error.missingDoseUnits");
			}
			
			// for the following elements Order.hbm.xml says: not-null="true"
			ValidationUtils.rejectIfEmpty(errors, "drug", "error.null");
			if (order.getDrug() != null) {
				ValidationUtils.rejectIfEmpty(errors, "drug.concept", "error.null");
			}
			
			if (!(order.getConcept() == null)) {
				if (!(order.getDrug() == null) && !(order.getDrug().getConcept().equals(order.getConcept()))) {
					errors.rejectValue("drug", "error.general");
					errors.rejectValue("concept", "error.concept");
					
				}
			}
		}
	}