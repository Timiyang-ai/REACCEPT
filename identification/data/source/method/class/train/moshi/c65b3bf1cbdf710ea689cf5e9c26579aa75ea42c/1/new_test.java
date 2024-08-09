  @Test public void serializeNulls() throws Exception {
    JsonAdapter<Map<String, String>> serializeNulls = new JsonAdapter<Map<String, String>>() {
      @Override public Map<String, String> fromJson(JsonReader reader) throws IOException {
        throw new AssertionError();
      }

      @Override public void toJson(JsonWriter writer, Map<String, String> map) throws IOException {
        writer.beginObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
          writer.name(entry.getKey()).value(entry.getValue());
        }
        writer.endObject();
      }
    }.serializeNulls();

    JsonWriter writer = factory.newWriter();
    serializeNulls.toJson(writer, Collections.<String, String>singletonMap("a", null));
    assertThat(factory.json()).isEqualTo("{\"a\":null}");
  }