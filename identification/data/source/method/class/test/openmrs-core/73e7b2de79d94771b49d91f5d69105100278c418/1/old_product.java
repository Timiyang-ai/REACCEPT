public void validate(Object obj, Errors errors) {
		OrderFrequency orderFrequency = (OrderFrequency) obj;
		if (orderFrequency == null) {
			errors.reject("error.general");
		} else {
			ValidationUtils.rejectIfEmpty(errors, "concept", "Concept.noConceptSelected");
			
			Concept concept = orderFrequency.getConcept();
			if (concept != null) {
				if (concept.getConceptClass() == null) {
					errors.rejectValue("concept", "OrderFrequency.concept.shouldBeClassFrequency");
				} else {
					if (!"Frequency".equals(concept.getConceptClass().getName())) {
						errors.rejectValue("concept", "OrderFrequency.concept.shouldBeClassFrequency");
					}
				}
				
				OrderFrequency of = Context.getOrderService().getOrderFrequencyByConcept(concept);
				if (of != null && !of.equals(orderFrequency)) {
					errors.rejectValue("concept", "OrderFrequency.concept.shouldNotBeShared");
				}
			}
		}
	}