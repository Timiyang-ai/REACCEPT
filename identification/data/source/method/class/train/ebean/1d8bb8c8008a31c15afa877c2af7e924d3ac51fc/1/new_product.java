protected static String cast(Object value, boolean asArray) {

    if (value == null) {
      // for exists and not-exists expressions
      return "";
    }
    if (value instanceof Integer) {
      return asArray ? "::integer[]" : "::integer";
    }
    if (value instanceof Long) {
      return asArray ? "::bigint[]" : "::bigint";
    }
    if (value instanceof Number) {
      return asArray ? "::decimal[]" : "::decimal";
    }
    if (value instanceof Boolean) {
      return asArray ? "::boolean[]" : "::boolean";
    }
    return "";
  }