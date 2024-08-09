@Test
	public void hasSameOrderableAs_shouldReturnFalseIfTheConceptsMatchAndOnlyTheOtherHasADrug() throws Exception {
		DrugOrder order = new DrugOrder();
		Concept concept = new Concept();
		order.setConcept(concept);
		
		DrugOrder otherOrder = new DrugOrder();
		Drug drug1 = new Drug();
		drug1.setConcept(concept);
		otherOrder.setDrug(drug1); //should set the concept
		assertEquals(order.getConcept(), otherOrder.getConcept());//sanity check
		
		assertFalse(order.hasSameOrderableAs(otherOrder));
	}