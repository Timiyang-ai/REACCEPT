public void validate(Object obj, Errors errors) {
		Order order = (Order) obj;
		if (order == null) {
			errors.rejectValue("order", "error.general");
		} else {
			if (order.getEncounter() != null && order.getPatient() != null) {
				if (!order.getEncounter().getPatient().equals(order.getPatient()))
					errors.rejectValue("encounter", "Order.error.encounterPatientMismatch");
			}
			
			// for the following elements Order.hbm.xml says: not-null="true"
			ValidationUtils.rejectIfEmpty(errors, "discontinued", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "voided", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "concept", "Concept.noConceptSelected");
			ValidationUtils.rejectIfEmpty(errors, "patient", "error.null");
			
			Date startDate = order.getStartDate();
			if (startDate != null) {
				Date discontinuedDate = order.getDiscontinuedDate();
				if (discontinuedDate != null && startDate.after(discontinuedDate)) {
					errors.rejectValue("startDate", "Order.error.startDateAfterDiscontinuedDate");
					errors.rejectValue("discontinuedDate", "Order.error.startDateAfterDiscontinuedDate");
				}
				
				Date autoExpireDate = order.getAutoExpireDate();
				if (autoExpireDate != null && startDate.after(autoExpireDate)) {
					errors.rejectValue("startDate", "Order.error.startDateAfterAutoExpireDate");
					errors.rejectValue("autoExpireDate", "Order.error.startDateAfterAutoExpireDate");
				}
			}
			
			if (order.getDiscontinued() == null) {
				errors.rejectValue("discontinued", "error.null");
			} else if (order.getDiscontinued()) {
				ValidationUtils.rejectIfEmpty(errors, "discontinuedDate", "Order.error.discontinueNeedsDateAndPerson");
				ValidationUtils.rejectIfEmpty(errors, "discontinuedBy", "Order.error.discontinueNeedsDateAndPerson");
				if (order.getDiscontinuedDate() != null) {
					// must be <= now(), and <= autoExpireDate
					if (OpenmrsUtil.compare(order.getDiscontinuedDate(), new Date()) > 0)
						errors.rejectValue("discontinuedDate", "Order.error.discontinuedDateInFuture");
					if (order.getAutoExpireDate() != null
					        && OpenmrsUtil.compare(order.getDiscontinuedDate(), order.getAutoExpireDate()) > 0)
						errors.rejectValue("discontinuedDate", "Order.error.discontinuedAfterAutoExpireDate");
				}
			}
		}
	}