public void validate(Object obj, Errors errors) {
		Order order = (Order)obj;
		if (order == null) {
			errors.rejectValue("order", "error.general");
		} else {
			ValidationUtils.rejectIfEmpty(errors, "patient", "error.null");
			if (order.getEncounter() != null && order.getPatient() != null) {
				if (!order.getEncounter().getPatient().equals(order.getPatient()))
					errors.rejectValue("encounter", "Order.error.encounterPatientMismatch");
			}
		}
	}