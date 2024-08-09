public static ResponseContext deserialize(String responseContext, ObjectMapper objectMapper) throws IOException
  {
    final Map<String, Object> keyNameToObjects = objectMapper.readValue(
        responseContext,
        JacksonUtils.TYPE_REFERENCE_MAP_STRING_OBJECT
    );
    final ResponseContext context = ResponseContext.createEmpty();
    keyNameToObjects.forEach((keyName, value) -> {
      final BaseKey key = Key.keyOf(keyName);
      context.add(key, value);
    });
    return context;
  }