@Override
	public Order cloneForRevision() {
		TestOrder newOrder = new TestOrder();
		newOrder.setCareSetting(this.getCareSetting());
		newOrder.setConcept(this.getConcept());
		newOrder.setAction(Action.REVISE);
		newOrder.setPreviousOrder(this);
		newOrder.setPatient(this.getPatient());
		newOrder.setInstructions(this.getInstructions());
		newOrder.setUrgency(this.getUrgency());
		newOrder.setCommentToFulfiller(this.getCommentToFulfiller());
		newOrder.setAccessionNumber(this.getAccessionNumber());
		newOrder.setAutoExpireDate(this.getAutoExpireDate());
		newOrder.setOrderReason(this.getOrderReason());
		newOrder.setOrderReasonNonCoded(this.getOrderReasonNonCoded());
		newOrder.setScheduledDate(this.getScheduledDate());
		newOrder.setSpecimenSource(getSpecimenSource());
		newOrder.setLaterality(getLaterality());
		newOrder.setClinicalHistory(getClinicalHistory());
		newOrder.setFrequency(getFrequency());
		newOrder.setNumberOfRepeats(getNumberOfRepeats());
		return newOrder;
		
	}