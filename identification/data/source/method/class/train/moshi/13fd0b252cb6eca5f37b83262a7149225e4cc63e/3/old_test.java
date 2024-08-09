  @Test public void indent() throws Exception {
    assumeTrue(factory.encodesToBytes());

    JsonAdapter<List<String>> indent = new JsonAdapter<List<String>>() {
      @Override public List<String> fromJson(JsonReader reader) throws IOException {
        throw new AssertionError();
      }

      @Override public void toJson(JsonWriter writer, List<String> value) throws IOException {
        writer.beginArray();
        for (String s : value) {
          writer.value(s);
        }
        writer.endArray();
      }
    }.indent("\t\t\t");

    JsonWriter writer = factory.newWriter();
    indent.toJson(writer, Arrays.asList("a", "b", "c"));
    assertThat(factory.json()).isEqualTo(""
        + "[\n"
        + "\t\t\t\"a\",\n"
        + "\t\t\t\"b\",\n"
        + "\t\t\t\"c\"\n"
        + "]");
  }