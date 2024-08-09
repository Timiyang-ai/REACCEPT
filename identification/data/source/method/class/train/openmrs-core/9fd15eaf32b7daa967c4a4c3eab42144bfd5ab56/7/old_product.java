public void validate(Object obj, Errors errors) {
		Order order = (Order) obj;
		if (order == null) {
			errors.reject("error.general");
		} else {
			// for the following elements Order.hbm.xml says: not-null="true"
			ValidationUtils.rejectIfEmpty(errors, "voided", "error.null");
			//For DrugOrders, the api will set the concept to drug.concept
			if (!DrugOrder.class.isAssignableFrom(order.getClass())) {
				ValidationUtils.rejectIfEmpty(errors, "concept", "Concept.noConceptSelected");
			}
			ValidationUtils.rejectIfEmpty(errors, "patient", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "encounter", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "orderer", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "urgency", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "action", "error.null");
			
			validateSamePatientInOrderAndEncounter(order, errors);
			validateOrderTypeClass(order, errors);
			validateStartDate(order, errors);
			validateScheduledDate(order, errors);
		}
	}