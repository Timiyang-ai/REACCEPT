  @Test public void nullSafe() throws Exception {
    JsonAdapter<String> toUpperCase = new JsonAdapter<String>() {
      @Override public String fromJson(JsonReader reader) throws IOException {
        return reader.nextString().toUpperCase(Locale.US);
      }

      @Override public void toJson(JsonWriter writer, String value) throws IOException {
        writer.value(value.toUpperCase(Locale.US));
      }
    }.nullSafe();

    JsonReader reader = factory.newReader("[\"a\", null, \"c\"]");
    reader.beginArray();
    assertThat(toUpperCase.fromJson(reader)).isEqualTo("A");
    assertThat(toUpperCase.fromJson(reader)).isNull();
    assertThat(toUpperCase.fromJson(reader)).isEqualTo("C");
    reader.endArray();

    JsonWriter writer = factory.newWriter();
    writer.beginArray();
    toUpperCase.toJson(writer, "a");
    toUpperCase.toJson(writer, null);
    toUpperCase.toJson(writer, "c");
    writer.endArray();
    assertThat(factory.json()).isEqualTo("[\"A\",null,\"C\"]");
  }