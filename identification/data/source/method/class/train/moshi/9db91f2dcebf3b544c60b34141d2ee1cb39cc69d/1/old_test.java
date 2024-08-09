  @Test public void nonNull() throws Exception {
    JsonAdapter<String> toUpperCase = new JsonAdapter<String>() {
      @Override public String fromJson(JsonReader reader) throws IOException {
        return reader.nextString().toUpperCase(Locale.US);
      }

      @Override public void toJson(JsonWriter writer, String value) throws IOException {
        writer.value(value.toUpperCase(Locale.US));
      }
    }.nonNull();

    JsonReader reader = factory.newReader("[\"a\", null, \"c\"]");
    reader.beginArray();
    assertThat(toUpperCase.fromJson(reader)).isEqualTo("A");
    try {
      toUpperCase.fromJson(reader);
      fail();
    } catch (JsonDataException expected) {
      assertThat(expected).hasMessage("Unexpected null at $[1]");
      assertThat(reader.nextNull()).isNull();
    }
    assertThat(toUpperCase.fromJson(reader)).isEqualTo("C");
    reader.endArray();

    JsonWriter writer = factory.newWriter();
    writer.beginArray();
    toUpperCase.toJson(writer, "a");
    try {
      toUpperCase.toJson(writer, null);
      fail();
    } catch (JsonDataException expected) {
      assertThat(expected).hasMessage("Unexpected null at $[1]");
      writer.nullValue();
    }
    toUpperCase.toJson(writer, "c");
    writer.endArray();
    assertThat(factory.json()).isEqualTo("[\"A\",null,\"C\"]");
  }