@Override
  public RequestParameter isValidSingleParam(String value) {
    try {
      Number number = parseNumber.apply(value);
      if (number != null && this.testMaximum(number) && this.testMinimum(number) && this.testMultipleOf(number)) {
        return RequestParameter.create(number);
      } else {
        throw ValidationException.ValidationExceptionFactory.generateNotMatchValidationException("Invalid number");
      }
    } catch (NumberFormatException e) {
      throw ValidationException.ValidationExceptionFactory.generateNotMatchValidationException("Value is not a valid number");
    }
  }