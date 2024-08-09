	@Test
	public void unretireDrug_shouldMarkDrugAsNotRetired() {
		String uuidOfDrugToCheck = "7e2323fa-0fa0-461f-9b59-6765997d849e";
		Drug drug = conceptService.getDrugByUuid(uuidOfDrugToCheck);
		conceptService.unretireDrug(drug);
		assertFalse(drug.getRetired());
	}