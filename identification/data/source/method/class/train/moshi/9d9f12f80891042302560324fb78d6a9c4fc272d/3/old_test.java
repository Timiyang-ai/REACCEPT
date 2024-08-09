  @Test public void failOnUnknown() throws Exception {
    JsonAdapter<String> alwaysSkip = new JsonAdapter<String>() {
      @Override public String fromJson(JsonReader reader) throws IOException {
        reader.skipValue();
        throw new AssertionError();
      }

      @Override public void toJson(JsonWriter writer, String value) throws IOException {
        throw new AssertionError();
      }
    }.failOnUnknown();

    JsonReader reader = factory.newReader("[\"a\"]");
    reader.beginArray();
    try {
      alwaysSkip.fromJson(reader);
      fail();
    } catch (JsonDataException expected) {
      assertThat(expected).hasMessage("Cannot skip unexpected STRING at $[0]");
    }
    assertThat(reader.nextString()).isEqualTo("a");
    reader.endArray();
  }