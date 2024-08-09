public void validate(Object obj, Errors errors) {
		Order order = (Order) obj;
		if (order == null) {
			errors.reject("error.general");
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
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderNumber", "error.null");
			
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
				ValidationUtils
				        .rejectIfEmptyOrWhitespace(errors, "discontinuedReason", "Order.error.discontinueNeedsReason");
				if (order.getDiscontinuedDate() != null) {
					// must be <= now(), and <= autoExpireDate
					if (OpenmrsUtil.compare(order.getDiscontinuedDate(), new Date()) > 0)
						errors.rejectValue("discontinuedDate", "Order.error.discontinuedDateInFuture");
					if (order.getAutoExpireDate() != null
					        && OpenmrsUtil.compare(order.getDiscontinuedDate(), order.getAutoExpireDate()) > 0) {
						errors.rejectValue("autoExpireDate", "Order.error.discontinuedAfterAutoExpireDate");
						errors.rejectValue("discontinuedDate", "Order.error.discontinuedAfterAutoExpireDate");
					}
				}
			} else if (!order.getDiscontinued()) {
				if (order.getDiscontinuedDate() != null)
					errors.rejectValue("discontinuedDate", "Order.error.notDiscontinuedShouldHaveNoDate");
				
				if (order.getDiscontinuedBy() != null)
					errors.rejectValue("discontinuedBy", "Order.error.notDiscontinuedShouldHaveNoBy");
				
				if (order.getDiscontinuedReason() != null)
					errors.rejectValue("discontinuedReason", "Order.error.notDiscontinuedShouldHaveNoReason");
			}
		}
	}