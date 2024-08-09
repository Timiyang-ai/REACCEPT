public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		
		DrugOrder order = (DrugOrder) obj;
		if (order == null) {
			errors.rejectValue("order", "error.general");
		} else {
			// for the following elements Order.hbm.xml says: not-null="true"
			ValidationUtils.rejectIfEmpty(errors, "prn", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "complex", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "drug", "error.null");
		}
	}