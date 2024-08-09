public void validate(Object obj, Errors errors) {
		if (obj == null || !(obj instanceof OrderType)) {
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type" + OrderType.class);
		} else {
			OrderType orderType = (OrderType) obj;
			
			String name = orderType.getName();
			if (!StringUtils.hasText(name)) {
				errors.rejectValue("name", "error.name");
			}
			
			OrderType ot = Context.getOrderService().getOrderTypeByName(name);
			if (ot != null && !orderType.equals(ot)) {
				errors.rejectValue("name", "OrderType.duplicate.name", "Duplicate order type name: " + name);
			}
		}
	}