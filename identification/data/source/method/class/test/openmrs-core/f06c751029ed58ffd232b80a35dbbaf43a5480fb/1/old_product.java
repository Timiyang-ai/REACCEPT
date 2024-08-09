public void validate(Object obj, Errors errors) {
		if (obj == null || !(obj instanceof OrderType)) {
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type" + OrderType.class);
		} else {
			OrderType orderType = (OrderType) obj;
			
			String name = orderType.getName();
			if (!StringUtils.hasText(name)) {
				errors.rejectValue("name", "error.name");
			}
			
			OrderType duplicate = Context.getOrderService().getOrderTypeByName(name);
			if (duplicate != null && !orderType.equals(duplicate)) {
				errors.rejectValue("name", "OrderType.duplicate.name", "Duplicate order type name: " + name);
			}
			
			for (OrderType ot : Context.getOrderService().getOrderTypes(true)) {
				if (ot != null) {
					if (OpenmrsUtil.nullSafeEquals(orderType.getJavaClassName(), ot.getJavaClassName())) {
						errors.rejectValue("javaClassName", "OrderType.duplicate.javaClass",
						    "Duplicate order type java class: " + ot.getJavaClassName());
					} else {
						for (ConceptClass cc : ot.getConceptClasses()) {
							if (cc != null && orderType.getConceptClasses().contains(cc)) {
								errors.rejectValue("conceptClasses", "OrderType.duplicate.conceptClass",
								    "Duplicate order type concept class: " + cc.getName());
							}
						}
					}
				}
			}
		}
	}