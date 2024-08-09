@Override
  public void isValid(String value) {
    try {
      NumberType number = parseNumber.apply(value);
      if (number != null && this.testMaximum(number) && this.testMinimum(number) && this.testMultipleOf(number)) {
        return;
      } else {
        throw ValidationException.generateNotMatchValidationException(null); //TODO add messages in future
      }
    } catch (NumberFormatException e) {
      throw ValidationException.generateNotMatchValidationException(null); //TODO add messages in future
    }
  }