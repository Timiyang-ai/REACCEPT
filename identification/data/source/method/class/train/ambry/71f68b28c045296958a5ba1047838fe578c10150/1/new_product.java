private static String getHeader(Map<String, List<String>> args, String header, boolean required)
      throws RestServiceException {
    String value = null;
    if (args.containsKey(header)) {
      List<String> values = args.get(header);
      if (values.size() == 1) {
        value = values.get(0);
        if (value == null && required) {
          throw new RestServiceException("Request has null value for header: " + header,
              RestServiceErrorCode.InvalidArgs);
        }
      } else {
        throw new RestServiceException("Request has too many values for header: " + header,
            RestServiceErrorCode.InvalidArgs);
      }
    } else if (required) {
      throw new RestServiceException("Request does not have required header: " + header,
          RestServiceErrorCode.MissingArgs);
    }
    return value;
  }