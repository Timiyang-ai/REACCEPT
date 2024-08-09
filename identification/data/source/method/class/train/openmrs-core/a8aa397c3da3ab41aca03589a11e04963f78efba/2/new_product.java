public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		
		DrugOrder order = (DrugOrder) obj;
		if (order == null) {
			errors.rejectValue("order", "error.general");
		} else {
			if (order.getDuration() != null)
				ValidationUtils.rejectIfEmpty(errors, "durationUnits", "DrugOrder.add.error.missingDurationUnits");
			
			if (order.getQuantity() != null)
				ValidationUtils.rejectIfEmpty(errors, "quantityUnits", "DrugOrder.add.error.missingQuantityUnits");
			
			if (order.getStrength() != null)
				ValidationUtils.rejectIfEmpty(errors, "strengthUnits", "DrugOrder.add.error.missingStrengthUnits");
			
			if (order.getDose() != null)
				ValidationUtils.rejectIfEmpty(errors, "doseUnits", "DrugOrder.add.error.missingDoseUnits");
			
		}
	}