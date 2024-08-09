  @Test public void selectString() throws IOException {
    JsonReader.Options abc = JsonReader.Options.of("a", "b", "c");

    JsonReader reader = newReader("[\"a\", \"b\", \"c\", \"d\"]");
    reader.beginArray();
    assertEquals("$[0]", reader.getPath());

    assertEquals(0, reader.selectString(abc));
    assertEquals("$[1]", reader.getPath());

    assertEquals(1, reader.selectString(abc));
    assertEquals("$[2]", reader.getPath());

    assertEquals(2, reader.selectString(abc));
    assertEquals("$[3]", reader.getPath());

    // A missed selectName() doesn't advance anything, not even the path.
    assertEquals(-1, reader.selectString(abc));
    assertEquals("$[3]", reader.getPath());
    assertEquals(JsonReader.Token.STRING, reader.peek());

    assertEquals("d", reader.nextString());
    assertEquals("$[4]", reader.getPath());

    reader.endArray();
  }