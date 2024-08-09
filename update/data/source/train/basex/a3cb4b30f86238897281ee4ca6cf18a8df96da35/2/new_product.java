private static void serialize(final String query, final String expected,
      final JsonFormat format) {
    try {
      final String actual = serialize(query, format);
      assertEquals("\n[E] " + expected + "\n[F] " + actual + '\n', expected, actual);
    } catch(final Exception ex) {
      fail(ex.toString());
    }
  }