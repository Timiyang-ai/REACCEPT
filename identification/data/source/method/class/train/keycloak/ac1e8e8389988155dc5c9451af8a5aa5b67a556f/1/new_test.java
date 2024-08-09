	@Test
	public void getJsonProperty_asJsonNode() throws IOException {
		TestProvider tested = getTested();

		JsonNode jsonNode = tested
				.asJsonNode("{\"nullone\":null, \"emptyone\":\"\", \"blankone\": \" \", \"withvalue\" : \"my value\", \"withbooleanvalue\" : true, \"withnumbervalue\" : 10}");
		Assert.assertNull(tested.getJsonProperty(jsonNode, "nonexisting"));
		Assert.assertNull(tested.getJsonProperty(jsonNode, "nullone"));
		Assert.assertNull(tested.getJsonProperty(jsonNode, "emptyone"));
		Assert.assertEquals(" ", tested.getJsonProperty(jsonNode, "blankone"));
		Assert.assertEquals("my value", tested.getJsonProperty(jsonNode, "withvalue"));
		Assert.assertEquals("true", tested.getJsonProperty(jsonNode, "withbooleanvalue"));
		Assert.assertEquals("10", tested.getJsonProperty(jsonNode, "withnumbervalue"));
	}