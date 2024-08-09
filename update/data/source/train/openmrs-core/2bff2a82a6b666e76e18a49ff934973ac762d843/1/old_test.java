@Test
	public void hasSameOrderableAs_shouldBeFalseIfConceptsDoNotMatch() {
		DrugOrder order = new DrugOrder();
		Drug drug1 = new Drug();
		drug1.setConcept(new Concept(123));
		order.setDrug(drug1);

		DrugOrder otherOrder = new DrugOrder();
		Drug drug2 = new Drug();
		drug2.setConcept(new Concept(456));
		otherOrder.setDrug(drug2);

		assertFalse(order.hasSameOrderableAs(otherOrder));
	}