@Override
	public void validate(Object target, Errors errors) {
		OrderGroup group = (OrderGroup) target;
		if (group == null) {
			errors.reject("error.general");
		} else {
			
			// for the following elements OrderGroup.hbm.xml says: not-null="true"
			ValidationUtils.rejectIfEmpty(errors, "creator", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "patient", "error.null");
			ValidationUtils.rejectIfEmpty(errors, "dateCreated", "error.null");
			
			if (group.getMembers() == null || group.getMembers().isEmpty())
				errors.rejectValue("members", "OrderGroup.noMembersPresent");
			else {
				int index = 0;
				for (Order order : group.getMembers()) {
					try {
						errors.pushNestedPath("members[" + index + "]");
						ValidationUtils.invokeValidator(orderValidator, order, errors);
						
						if (order.getPatient() != null && !order.getPatient().equals(group.getPatient()))
							errors.rejectValue("patient", "OrderGroup.orderPatientMatching");
					}
					finally {
						errors.popNestedPath();
						index++;
					}
					
				}
			}
			
		}
		
	}