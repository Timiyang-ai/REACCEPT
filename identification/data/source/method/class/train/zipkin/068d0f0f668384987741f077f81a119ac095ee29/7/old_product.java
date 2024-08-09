public Builder traceId(long high, long low) {
      if (high == 0L && low == 0L) throw new IllegalArgumentException("empty trace ID");
      char[] result = new char[high != 0L ? 32 : 16];
      int pos = 0;
      if (high != 0L) {
        writeHexLong(result, pos, high);
        pos += 16;
      }
      writeHexLong(result, pos, low);
      this.traceId = new String(result);
      return this;
    }