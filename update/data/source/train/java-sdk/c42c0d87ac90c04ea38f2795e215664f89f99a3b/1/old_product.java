public Request build() {
    final Request.Builder builder = new Request.Builder();
    // URL
    builder.url(toUrl());

    // POST/PUT require a body so send an empty body if the actual is null
    RequestBody requestBody = body;
    if (body == null)
      requestBody = RequestBody.create(null, new byte[0]);

    if (!formParams.isEmpty()) {
      final FormBody.Builder formBody = new FormBody.Builder();
      for (final NameValue param : formParams) {
        final String value = param.getValue() != null ? param.getValue() : "";
        formBody.add(param.getName(), value);
      }
      requestBody = formBody.build();
    }
    
    //accept application/json by default
    builder.header(HttpHeaders.ACCEPT, HttpMediaType.APPLICATION_JSON);

    if (!headers.isEmpty()) {
      for (final NameValue header : headers) {
        builder.header(header.getName(), header.getValue());
      }
    }

    switch (method) {
      case GET:
        builder.get();
        break;
      case POST:
        builder.post(requestBody);
        break;
      case PUT:
        builder.put(requestBody);
        break;
      case DELETE:
        builder.delete(requestBody);
        break;
    }

    return builder.build();
  }