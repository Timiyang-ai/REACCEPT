public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		
		DrugOrder order = (DrugOrder) obj;
		if (order == null) {
			errors.rejectValue("order", "error.general");
		} else {
			// for the following elements Order.hbm.xml says: not-null="true"
			ValidationUtils.rejectIfEmpty(errors, "prn", "error.null");
			
			if (order.isSigned()) {
				ValidationUtils.rejectIfEmpty(errors, "signedBy", "error.null");
				ValidationUtils.rejectIfEmpty(errors, "dateSigned", "error.null");
			}
			
			if (order.isActivated()) {
				ValidationUtils.rejectIfEmpty(errors, "activatedBy", "error.null");
				ValidationUtils.rejectIfEmpty(errors, "dateActivated", "error.null");
			}
			
			if (order.getDuration() != null)
				ValidationUtils.rejectIfEmpty(errors, "durationUnits", "DrugOrder.add.error.missingDurationUnits");
			
			if (order.getQuantity() != null)
				ValidationUtils.rejectIfEmpty(errors, "quantityUnits", "DrugOrder.add.error.missingQuantityUnits");
			
			if (order.getStrength() != null)
				ValidationUtils.rejectIfEmpty(errors, "strengthUnits", "DrugOrder.add.error.missingStrengthUnits");
			
		}
	}