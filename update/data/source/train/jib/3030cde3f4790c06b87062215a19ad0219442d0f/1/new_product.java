default T handleHttpResponseException(ResponseException responseException)
      throws ResponseException, RegistryErrorException {
    throw responseException;
  }