  @Test public void enterPath_nested() throws IOException {
    String content = "{\n"
      + "  \"name\" : \"Kamal\",\n"
      + "  \"cluster_name\" : \"elasticsearch\",\n"
      + "  \"version\" : {\n"
      + "    \"number\" : \"2.4.0\",\n"
      + "    \"build_hash\" : \"ce9f0c7394dee074091dd1bc4e9469251181fc55\",\n"
      + "    \"build_timestamp\" : \"2016-08-29T09:14:17Z\",\n"
      + "    \"build_snapshot\" : false,\n"
      + "    \"lucene_version\" : \"5.5.2\"\n"
      + "  },\n"
      + "  \"tagline\" : \"You Know, for Search\"\n"
      + "}";

    assertThat(
      JsonReaders.enterPath(JSON_FACTORY.createParser(content), "version", "number").getText())
      .isEqualTo("2.4.0");
  }