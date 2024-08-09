public static String parametersToString(Object... objs) {
    StringBuilder sb = new StringBuilder("(");
    if (objs != null) {
      for (int k = 0; k < objs.length; k ++) {
        if (k != 0) {
          sb.append(", ");
        }
        if (objs[k] == null) {
          sb.append("null");
        } else {
          sb.append(objs[k].toString());
        }
      }
    }
    sb.append(")");
    return sb.toString();
  }