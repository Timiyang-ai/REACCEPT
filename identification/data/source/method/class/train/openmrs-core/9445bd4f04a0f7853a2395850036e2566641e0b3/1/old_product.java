public boolean isType(OrderType orderType) {
		if (this.orderType.equals(orderType)) {
			return true;
		}
		OrderType parentType = this.orderType.getParent();
		while (parentType != null) {
			if (parentType.equals(orderType)) {
				return true;
			}
			parentType = parentType.getParent();
		}
		return false;
	}