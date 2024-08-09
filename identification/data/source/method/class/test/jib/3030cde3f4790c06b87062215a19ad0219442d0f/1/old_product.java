public static ErrorCodes getErrorCode(HttpResponseException responseException)
      throws HttpResponseException {
    // Obtain the error response code.
    String errorContent = responseException.getContent();
    if (errorContent == null) {
      throw responseException;
    }

    try {
      ErrorResponseTemplate errorResponse =
          JsonTemplateMapper.readJson(errorContent, ErrorResponseTemplate.class);
      List<ErrorEntryTemplate> errors = errorResponse.getErrors();
      // There may be multiple error objects
      if (errors.size() == 1) {
        String errorCodeString = errors.get(0).getCode();
        // May not get an error code back.
        if (errorCodeString != null) {
          // throws IllegalArgumentException if unknown error code
          return ErrorCodes.valueOf(errorCodeString);
        }
      }

    } catch (IOException | IllegalArgumentException ex) {
      // Parse exception: either isn't an error object or unknown error code
    }

    // rethrow the original exception
    throw responseException;
  }