@Override long readLong() {
      require(8);
      int pos = this.offset;
      this.offset = pos + 8;
      return (buf[pos] & 0xffL) << 56
        | (buf[pos + 1] & 0xffL) << 48
        | (buf[pos + 2] & 0xffL) << 40
        | (buf[pos + 3] & 0xffL) << 32
        | (buf[pos + 4] & 0xffL) << 24
        | (buf[pos + 5] & 0xffL) << 16
        | (buf[pos + 6] & 0xffL) << 8
        | (buf[pos + 7] & 0xffL);
    }