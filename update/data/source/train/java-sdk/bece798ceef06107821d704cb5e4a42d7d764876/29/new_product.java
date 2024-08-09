public String extract(final String text) {
    if (dataset == null)
      throw new IllegalArgumentException("dataset cannot be null");
    if (text == null)
      throw new IllegalArgumentException("text cannot be null");

    final Request request =
        RequestBuilder.post("/v1/sire/0")
            .withForm("sid", dataset.getId(), "rt", "xml", "txt", text).build();
    final Response response = execute(request);
    return ResponseUtil.getString(response);
  }