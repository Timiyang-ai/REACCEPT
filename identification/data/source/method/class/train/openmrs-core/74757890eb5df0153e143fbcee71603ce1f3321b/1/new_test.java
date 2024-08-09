	@Test
	public void getAttribute_shouldPersonAttributeBasedOnAttributeName() {
		Person person = personHelper(false, 1, 2, 3, "name1", "name2", "name3", "value1", "value2", "value3");
		Assert.assertEquals("name3", person.getAttribute("name3").getAttributeType().getName());
	}