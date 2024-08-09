default T handleHttpResponseException(HttpResponseException httpResponseException)
      throws HttpResponseException, RegistryErrorException {
    throw httpResponseException;
  }