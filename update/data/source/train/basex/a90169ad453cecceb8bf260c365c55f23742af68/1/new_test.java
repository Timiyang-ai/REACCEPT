@Test
  public void contentType() throws Exception {
    assertMediaType(mediaType("?query=1"), MediaType.APPLICATION_XML);
    assertMediaType(mediaType("?command=info"), MediaType.TEXT_PLAIN);

    assertMediaType(mediaType("?query=1&method=xml"), MediaType.APPLICATION_XML);
    assertMediaType(mediaType("?query=1&method=xhtml"), MediaType.TEXT_HTML);
    assertMediaType(mediaType("?query=1&method=html"), MediaType.TEXT_HTML);
    assertMediaType(mediaType("?query=1&method=text"), MediaType.TEXT_PLAIN);
    assertMediaType(mediaType("?query=1&method=raw"), MediaType.APPLICATION_OCTET_STREAM);
    assertMediaType(mediaType("?query=<json+type='object'/>&method=json"),
        MediaType.APPLICATION_JSON);

    assertMediaType(mediaType("?query=1&media-type=application/xml"), MediaType.APPLICATION_XML);
    assertMediaType(mediaType("?query=1&media-type=text/html"), MediaType.TEXT_HTML);
    assertMediaType(mediaType("?query=1&media-type=xxx"), new MediaType("xxx"));
  }