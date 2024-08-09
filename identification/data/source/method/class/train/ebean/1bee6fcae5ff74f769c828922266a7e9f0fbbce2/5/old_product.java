public String nextVersion() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < ordering.length; i++) {
      if (i < ordering.length -1 ) {
        sb.append(ordering[i]).append(".");
      } else {
        sb.append(ordering[i]+1);
      }
    }
    return sb.toString();
  }