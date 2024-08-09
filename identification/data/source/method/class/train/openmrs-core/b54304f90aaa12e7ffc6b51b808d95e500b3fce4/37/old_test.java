	@Test
	public void deserializeSimpleConfiguration_shouldDeserializeAConfigurationSerializedByTheCorrespondingSerializeMethod() {
		Map<String, String> config = new HashMap<>();
		config.put("one property", "one value");
		config.put("another property", "another value < with > strange&nbsp;characters");
		
		String serialized = CustomDatatypeUtil.serializeSimpleConfiguration(config);
		Map<String, String> deserialized = CustomDatatypeUtil.deserializeSimpleConfiguration(serialized);
		Assert.assertEquals(2, deserialized.size());
		Assert.assertEquals("one value", deserialized.get("one property"));
		Assert.assertEquals("another value < with > strange&nbsp;characters", deserialized.get("another property"));
	}