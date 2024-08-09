@Override
	public Order cloneForRevision() {
		DrugOrder newOrder = new DrugOrder();
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
		newOrder.setScheduledDate(getScheduledDate());
		newOrder.setDose(this.getDose());
		newOrder.setDoseUnits(this.getDoseUnits());
		newOrder.setFrequency(this.getFrequency());
		newOrder.setAsNeeded(this.getAsNeeded());
		newOrder.setAsNeededCondition(this.getAsNeededCondition());
		newOrder.setQuantity(this.getQuantity());
		newOrder.setQuantityUnits(this.getQuantityUnits());
		newOrder.setDrug(this.getDrug());
		newOrder.setDosingType(this.getDosingType());
		newOrder.setDosingInstructions(this.getDosingInstructions());
		newOrder.setDuration(this.getDuration());
		newOrder.setDurationUnits(this.getDurationUnits());
		newOrder.setRoute(this.getRoute());
		newOrder.setAdministrationInstructions(this.getAdministrationInstructions());
		newOrder.setNumRefills(this.getNumRefills());
		return newOrder;
	}