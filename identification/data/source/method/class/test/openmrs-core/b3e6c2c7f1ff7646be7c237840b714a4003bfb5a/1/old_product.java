public Order cloneForRevision() {
		Order newOrder = new Order();
		newOrder.setCareSetting(getCareSetting());
		newOrder.setConcept(getConcept());
		if (getAction() == Action.DISCONTINUE) {
			newOrder.setAction(Action.DISCONTINUE);
			newOrder.setPreviousOrder(getPreviousOrder());
			newOrder.setStartDate(getStartDate());
		} else {
			newOrder.setAction(Action.REVISE);
			newOrder.setPreviousOrder(this);
			newOrder.setAutoExpireDate(getAutoExpireDate());
		}
		newOrder.setPatient(getPatient());
		newOrder.setOrderType(getOrderType());
		newOrder.setScheduledDate(getScheduledDate());
		newOrder.setInstructions(getInstructions());
		newOrder.setUrgency(getUrgency());
		newOrder.setCommentToFulfiller(getCommentToFulfiller());
		newOrder.setOrderReason(getOrderReason());
		newOrder.setOrderReasonNonCoded(getOrderReasonNonCoded());
		return newOrder;
	}