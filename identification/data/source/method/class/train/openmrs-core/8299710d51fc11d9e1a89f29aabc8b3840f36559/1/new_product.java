@Override
	public DrugOrder cloneForRevision() {
		DrugOrder newOrder = new DrugOrder();
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
		newOrder.setDose(getDose());
		newOrder.setDoseUnits(getDoseUnits());
		newOrder.setFrequency(getFrequency());
		newOrder.setAsNeeded(getAsNeeded());
		newOrder.setAsNeededCondition(getAsNeededCondition());
		newOrder.setQuantity(getQuantity());
		newOrder.setQuantityUnits(getQuantityUnits());
		newOrder.setDrug(getDrug());
		newOrder.setDosingType(getDosingType());
		newOrder.setDosingInstructions(getDosingInstructions());
		newOrder.setDuration(getDuration());
		newOrder.setDurationUnits(getDurationUnits());
		newOrder.setRoute(getRoute());
		newOrder.setNumRefills(getNumRefills());
		return newOrder;
	}