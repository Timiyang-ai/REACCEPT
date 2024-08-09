@Test
	@Verifies(value = "should match on patient identifiers", method = "findPeopleByRoles(String,null,String)")
	public void findPeopleByRoles_shouldMatchOnPatientIdentifiers() throws Exception {
		DWRPersonService dwrPersonService = new DWRPersonService();
		
		List<PersonListItem> persons = dwrPersonService.findPeopleByRoles("12345K", false, null);
		
		Assert.assertEquals(1, persons.size());
		Assert.assertEquals(new PersonListItem(6), persons.get(0));
	}