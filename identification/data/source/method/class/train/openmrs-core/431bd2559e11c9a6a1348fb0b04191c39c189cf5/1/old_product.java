@Override
	public void validate(Object arg0, Errors errors) {
		OrderGroup group = (OrderGroup) arg0;
		if (group == null) {
			errors.reject("group", "error.general");
		} else {
			
			// for the following elements OrderGroup.hbm.xml says: not-null="true"
			ValidationUtils.rejectIfEmpty(errors, "creator", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "patient", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "dateCreated", "error.null");
			
			if (group.getMembers() == null || group.getMembers().isEmpty())
				errors.reject("OrderGroup.noMembersPresent");
			else {
				int index = 0;
				for (Order order : group.getMembers()) {
					try {
						errors.pushNestedPath("members[" + index + "]");
						ValidationUtils.invokeValidator(orderValidator, order, errors);
					}
					finally {
						errors.popNestedPath();
						index++;
					}
					if (order.getPatient() != null && !order.getPatient().equals(group.getPatient()))
						errors.reject("OrderGroup.orderPatientMatching");
				}
			}
			
		}
		
	}