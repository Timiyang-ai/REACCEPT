  @Test public void selectName() throws IOException {
    JsonReader.Options abc = JsonReader.Options.of("a", "b", "c");

    JsonReader reader = newReader("{\"a\": 5, \"b\": 5, \"c\": 5, \"d\": 5}");
    reader.beginObject();
    assertEquals("$.", reader.getPath());

    assertEquals(0, reader.selectName(abc));
    assertEquals("$.a", reader.getPath());
    assertEquals(5, reader.nextInt());
    assertEquals("$.a", reader.getPath());

    assertEquals(1, reader.selectName(abc));
    assertEquals("$.b", reader.getPath());
    assertEquals(5, reader.nextInt());
    assertEquals("$.b", reader.getPath());

    assertEquals(2, reader.selectName(abc));
    assertEquals("$.c", reader.getPath());
    assertEquals(5, reader.nextInt());
    assertEquals("$.c", reader.getPath());

    // A missed selectName() doesn't advance anything, not even the path.
    assertEquals(-1, reader.selectName(abc));
    assertEquals("$.c", reader.getPath());
    assertEquals(JsonReader.Token.NAME, reader.peek());

    assertEquals("d", reader.nextName());
    assertEquals("$.d", reader.getPath());
    assertEquals(5, reader.nextInt());
    assertEquals("$.d", reader.getPath());

    reader.endObject();
  }