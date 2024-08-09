public void writeNum(final int value) {
    if(value < 0 || value > 0x3FFFFFFF) {
      write(0xC0); write(value >>> 24); write(value >>> 16); write(value >>> 8); write(value);
    } else if(value > 0x3FFF) {
      write(value >>> 24 | 0x80); write(value >>> 16);
      write(value >>> 8); write(value);
    } else if(value > 0x3F) {
      write(value >>> 8 | 0x40); write(value);
    } else {
      write(value);
    }
  }