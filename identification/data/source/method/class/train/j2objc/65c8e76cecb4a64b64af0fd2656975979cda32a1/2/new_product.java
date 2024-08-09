public String toString(int indentFactor) throws JSONException {
    StringWriter sw = new StringWriter();
    synchronized (sw.getBuffer()) {
      return this.write(sw, indentFactor, 0).toString();
    }
  }