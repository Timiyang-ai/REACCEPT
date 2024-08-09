public Request build() {
    final Request.Builder builder = new Request.Builder();
    // URL
    builder.url(toUrl());


    if (method == HTTPMethod.GET) {
      Validator.isNull(body, "cannot send a RequestBody in a GET request");
    } else if (!formParams.isEmpty()) {
      // The current behaviour of the RequestBuilder is to replace the body when formParams is present
      final FormBody.Builder formBody = new FormBody.Builder();
      for (final NameValue param : formParams) {
        final String value = param.getValue() != null ? param.getValue() : "";
        formBody.add(param.getName(), value);
      }
      body = formBody.build();
    } else if (body == null) {
      // POST/PUT require a body so send an empty body if the actual is null
      // DELETE allows an empty request body
      body = RequestBody.create(null, new byte[0]);
    }
    builder.method(method.name(), body);
    
    //accept application/json by default
    builder.header(HttpHeaders.ACCEPT, HttpMediaType.APPLICATION_JSON);

    for (final NameValue header : headers) {
      builder.header(header.getName(), header.getValue());
    }

    return builder.build();
  }