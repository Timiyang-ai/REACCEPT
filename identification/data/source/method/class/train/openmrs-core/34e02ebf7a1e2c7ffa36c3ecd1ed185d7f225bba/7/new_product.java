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
		}
	}