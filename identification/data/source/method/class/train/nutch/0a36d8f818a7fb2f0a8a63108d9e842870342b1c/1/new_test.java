@Test
  public void testFilter() {
    // check that we get the same values
    Map<CharSequence, ByteBuffer> meta = parseMetaTags(sampleFile, false);

    assertEquals(description,
        getMeta(meta, MetaTagsParser.PARSE_META_PREFIX + "description"));
    assertEquals(keywords,
        getMeta(meta, MetaTagsParser.PARSE_META_PREFIX + "keywords"));
  }