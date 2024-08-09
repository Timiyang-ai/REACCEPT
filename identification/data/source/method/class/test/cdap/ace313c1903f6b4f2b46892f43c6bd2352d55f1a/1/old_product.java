private HttpResponse deploy(String filename) throws Exception {
    File archive = FileUtils.toFile(getClass().getResource("/" + filename));
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try {
      ByteStreams.copy(new FileInputStream(archive), bos);
    } finally {
      bos.close();
    }

    HttpPut put = GatewayFastTestsSuite.getPUT("/v2/apps");
    put.setHeader(GatewayAuthenticator.CONTINUUITY_API_KEY, "api-key-example");
    put.setHeader("X-Archive-Name", filename);
    put.setEntity(new ByteArrayEntity(bos.toByteArray()));
    return GatewayFastTestsSuite.PUT(put);
  }