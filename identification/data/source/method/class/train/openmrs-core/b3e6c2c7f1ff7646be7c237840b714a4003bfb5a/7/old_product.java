public Order cloneForRevision() {
		Order newOrder = new Order();
		newOrder.setCareSetting(this.getCareSetting());
		newOrder.setConcept(this.getConcept());
		newOrder.setAction(Action.REVISE);
		newOrder.setPreviousOrder(this);
		newOrder.setPatient(this.getPatient());
		
		newOrder.setInstructions(this.getInstructions());
		newOrder.setUrgency(this.getUrgency());
		newOrder.setCommentToFulfiller(this.getCommentToFulfiller());
		return newOrder;
	}