	private static MosaicDescriptor readFrom(final String description) {
		// Arrange:
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("description", description);
		final JsonDeserializer deserializer = new JsonDeserializer(jsonObject, null);

		// Act:
		return MosaicDescriptor.readFrom(deserializer, "description");
	}