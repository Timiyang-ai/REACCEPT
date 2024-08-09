public Builder traceId(long high, long low) {
      if (high == 0L && low == 0L) throw new IllegalArgumentException("empty trace ID");
      char[] data = Platform.get().idBuffer();
      int pos = 0;
      if (high != 0L) {
        writeHexLong(data, pos, high);
        pos += 16;
      }
      writeHexLong(data, pos, low);
      this.traceId = new String(data, 0, high != 0L ? 32 : 16);
      return this;
    }