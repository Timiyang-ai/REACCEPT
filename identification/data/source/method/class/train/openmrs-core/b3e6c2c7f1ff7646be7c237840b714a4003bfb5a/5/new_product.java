public Order cloneForRevision() {
		Order newOrder = new Order();
		newOrder.setCareSetting(this.getCareSetting());
		newOrder.setConcept(this.getConcept());
		newOrder.setAction(Action.REVISE);
		newOrder.setPreviousOrder(this);
		newOrder.setPatient(this.getPatient());
		newOrder.setOrderType(this.getOrderType());
		newOrder.setScheduledDate(getScheduledDate());
		newOrder.setInstructions(this.getInstructions());
		newOrder.setUrgency(this.getUrgency());
		newOrder.setCommentToFulfiller(this.getCommentToFulfiller());
		newOrder.setAccessionNumber(this.getAccessionNumber());
		newOrder.setAutoExpireDate(this.getAutoExpireDate());
		newOrder.setOrderReason(this.getOrderReason());
		newOrder.setOrderReasonNonCoded(this.getOrderReasonNonCoded());
		return newOrder;
	}