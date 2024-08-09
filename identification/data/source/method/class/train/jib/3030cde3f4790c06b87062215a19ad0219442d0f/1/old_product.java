default T handleHttpResponseException(HttpResponseException responseException)
      throws HttpResponseException, RegistryErrorException {
    throw responseException;
  }