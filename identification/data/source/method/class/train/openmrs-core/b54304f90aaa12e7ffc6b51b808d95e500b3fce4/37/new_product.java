@SuppressWarnings("unchecked")
	public static Map<String, String> deserializeSimpleConfiguration(String serializedConfig) {
		if (StringUtils.isBlank(serializedConfig)) {
			return Collections.emptyMap();
		}
		try {
			return Context.getSerializationService().getDefaultSerializer().deserialize(serializedConfig, Map.class);
		}
		catch (SerializationException ex) {
			throw new APIException(ex);
		}
	}