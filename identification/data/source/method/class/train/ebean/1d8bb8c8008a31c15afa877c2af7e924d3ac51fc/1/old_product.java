protected static String cast(Object value, boolean asArray) {

    if (value == null) {
      // for exists and not-exists expressions
      return "";
    }

    if (isIntegerType(value)) {
      return asArray ? "::integer[]" : "::integer";
    }
    if (isNumberType(value)) {
      return asArray ? "::decimal[]" : "::decimal";
    }
    if (isBooleanType(value)) {
      return asArray ? "::boolean[]" : "::boolean";
    }

    return "";
  }