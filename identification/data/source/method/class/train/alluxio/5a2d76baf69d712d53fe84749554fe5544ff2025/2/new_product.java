public static String parametersToString(Object... objs) {
    StringBuilder sb = new StringBuilder("(");
    if (objs != null) {
      for (Object obj : objs) {
        if (sb.length() != 1) {
          sb.append(", ");
        }
        if (obj == null) {
          sb.append("null");
        } else {
          sb.append(obj.toString());
        }
      }
    }
    sb.append(")");
    return sb.toString();
  }