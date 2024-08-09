@Override
  public String toString() {
    String s = "";
    for (String prefix : LIST)
      s += ";" + prefix;
    return s.startsWith(";") ? s.substring(1) : s;
  }