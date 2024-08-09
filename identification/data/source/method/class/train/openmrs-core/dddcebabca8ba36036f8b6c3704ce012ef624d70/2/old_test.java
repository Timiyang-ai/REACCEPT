	@Test
	public void hasSameOrderableAs_shouldReturnFalseIfTheOtherOrderIsNull() {
		DrugOrder order = new DrugOrder();
		order.setConcept(new Concept());
		
		assertFalse(order.hasSameOrderableAs(null));
	}