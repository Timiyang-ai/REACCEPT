@Override
	public Order cloneForRevision() {
		TestOrder newOrder = new TestOrder();
		newOrder.setCareSetting(this.getCareSetting());
		newOrder.setConcept(this.getConcept());
		newOrder.setAction(Action.REVISE);
		newOrder.setPreviousOrder(this);
		newOrder.setPatient(this.getPatient());
		
		newOrder.setSpecimenSource(getSpecimenSource());
		newOrder.setLaterality(getLaterality());
		newOrder.setClinicalHistory(getClinicalHistory());
		newOrder.setFrequency(getFrequency());
		newOrder.setNumberOfRepeats(getNumberOfRepeats());
		return newOrder;
		
	}