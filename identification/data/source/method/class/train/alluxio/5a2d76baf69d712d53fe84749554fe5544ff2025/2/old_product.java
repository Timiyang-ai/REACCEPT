public static String parametersToString(Object... objs) {
    StringBuilder sb = new StringBuilder("(");
    for (Object obj : objs) {
      if (sb.length() != 1) {
        sb.append(", ");
      }
      sb.append(obj.toString());
    }
    sb.append(")");
    return sb.toString();
  }