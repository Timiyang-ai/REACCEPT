public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		
		DrugOrder order = (DrugOrder) obj;
		if (order == null) {
			errors.rejectValue("order", "error.general");
		} else {
			ValidationUtils.rejectIfEmpty(errors, "concept", "error.null");
			// for the following elements Order.hbm.xml says: not-null="true"
			ValidationUtils.rejectIfEmpty(errors, "prn", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "complex", "error.null");
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
			if (order.getDose() != null || order.getEquivalentDailyDose() != null || order.getQuantity() != null) {
				ValidationUtils.rejectIfEmpty(errors, "units", "DrugOrder.error.unitsNotSetWhenDoseOrQuantitySpecified");
			}
		}
	}