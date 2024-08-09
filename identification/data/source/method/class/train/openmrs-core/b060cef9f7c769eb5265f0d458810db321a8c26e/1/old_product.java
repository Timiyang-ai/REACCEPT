public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		TestOrder order = (TestOrder) obj;
		if (order == null) {
			errors.reject("error.general");
		} else {
			if (order.getSpecimenSource() != null) {
				List<Concept> specimenSources = Context.getOrderService().getTestSpecimenSources();
				if (!specimenSources.contains(order.getSpecimenSource())) {
					errors.rejectValue("specimenSource", "TestOrder.error.specimenSourceNotAmongAllowedConcepts");
				}
			}
		}
	}