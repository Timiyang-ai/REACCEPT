@Override
  public RequestParameter isValid(String value) {
    if (value == null || value.length() == 0)
      return RequestParameter.create(getDefault());
    try {
      NumberType number = parseNumber.apply(value);
      if (number != null && this.testMaximum(number) && this.testMinimum(number) && this.testMultipleOf(number)) {
        return RequestParameter.create(number);
      } else {
        throw ValidationException.generateNotMatchValidationException("Invalid number");
      }
    } catch (NumberFormatException e) {
      throw ValidationException.generateNotMatchValidationException("Value is not a valid number");
    }
  }