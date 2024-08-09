@Override
  public boolean isValid(String value) {
    try {
      NumberType number = parseNumber.apply(value);
      if (number != null && this.testMaximum(number) && this.testMinimum(number) && this.testMultipleOf(number)) {
        return true;
      } else {
        return false;
      }
    } catch (NumberFormatException e) {
      return false;
    }
  }