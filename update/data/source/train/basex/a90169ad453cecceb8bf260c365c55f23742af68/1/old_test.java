@Test
  public void contentType() throws Exception {
    assertMediaType(contentType("?query=1"), MediaType.APPLICATION_XML);
    assertMediaType(contentType("?command=info"), MediaType.TEXT_PLAIN);

    assertMediaType(contentType("?query=1&method=xml"), MediaType.APPLICATION_XML);
    assertMediaType(contentType("?query=1&method=xhtml"), MediaType.TEXT_HTML);
    assertMediaType(contentType("?query=1&method=html"), MediaType.TEXT_HTML);
    assertMediaType(contentType("?query=1&method=text"), MediaType.TEXT_PLAIN);
    assertMediaType(contentType("?query=1&method=raw"), MediaType.APPLICATION_OCTET_STREAM);
    assertMediaType(contentType("?query=<json+type='object'/>&method=json"),
        MediaType.APPLICATION_JSON);

    assertMediaType(contentType("?query=1&media-type=application/xml"), MediaType.APPLICATION_XML);
    assertMediaType(contentType("?query=1&media-type=text/html"), MediaType.TEXT_HTML);
    assertMediaType(contentType("?query=1&media-type=xxx"), new MediaType("xxx"));
  }