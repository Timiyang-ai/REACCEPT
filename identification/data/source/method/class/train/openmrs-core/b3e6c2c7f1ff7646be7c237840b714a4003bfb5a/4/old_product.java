@Override
	public TestOrder cloneForRevision() {
		TestOrder newOrder = new TestOrder();
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
		newOrder.setInstructions(getInstructions());
		newOrder.setUrgency(getUrgency());
		newOrder.setCommentToFulfiller(getCommentToFulfiller());
		newOrder.setOrderReason(getOrderReason());
		newOrder.setOrderReasonNonCoded(getOrderReasonNonCoded());
		newOrder.setScheduledDate(getScheduledDate());
		newOrder.setSpecimenSource(getSpecimenSource());
		newOrder.setLaterality(getLaterality());
		newOrder.setClinicalHistory(getClinicalHistory());
		newOrder.setFrequency(getFrequency());
		newOrder.setNumberOfRepeats(getNumberOfRepeats());
		return newOrder;
		
	}