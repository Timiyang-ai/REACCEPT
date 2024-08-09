	@Test
	public void getAttributes_shouldReturnAllPersonAttributesWithMatchingAttributeTypeNames() {
		Person person = personHelper(false, 1, 2, 3, "name1", "name1", "name3", "value1", "value2", "value3");
		Assert.assertEquals(2, person.getAttributes("name1").size());
	}