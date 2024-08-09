@Override
	public void validate(Object obj, Errors errors) {
		if (obj == null || !(obj instanceof OrderType)) {
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type" + OrderType.class);
		} else {
			OrderType orderType = (OrderType) obj;
			String name = orderType.getName();
			if (!StringUtils.hasText(name)) {
				errors.rejectValue("name", "error.name");
				return;
			}
			
			if (orderType.getParent() != null && OrderUtil.isType(orderType, orderType.getParent())) {
				errors.rejectValue("parent", "OrderType.parent.amongDescendants", new Object[] { orderType.getName() },
				    "Parent of " + orderType.getName() + " is among its descendants");
			}
			
			OrderType duplicate = Context.getOrderService().getOrderTypeByName(name);
			if (duplicate != null && !orderType.equals(duplicate)) {
				errors.rejectValue("name", "OrderType.duplicate.name", "Duplicate order type name: " + name);
			}
			
			for (OrderType ot : Context.getOrderService().getOrderTypes(true)) {
				if (ot != null) {
					//If this was an edit, skip past the order we are actually validating 
					if (orderType.equals(ot)) {
						continue;
					}
					int index = 0;
					for (ConceptClass cc : ot.getConceptClasses()) {
						if (cc != null && orderType.getConceptClasses().contains(cc)) {
							errors.rejectValue("conceptClasses[" + index + "]", "OrderType.duplicate", new Object[] {
							        cc.getName(), orderType.getName() }, cc.getName()
							        + " is already associated to another order type:" + orderType.getName());
						}
						index++;
					}
				}
			}
		}
	}