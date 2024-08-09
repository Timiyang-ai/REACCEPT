	@Test
	public void retireDrug_shouldRetireTheGivenDrug() {
		String uuidOfDrugToCheck = "05ec820a-d297-44e3-be6e-698531d9dd3f";
		Drug drug = conceptService.getDrugByUuid(uuidOfDrugToCheck);
		conceptService.retireDrug(drug, "some dummy reason");
		assertTrue(drug.getRetired());
	}