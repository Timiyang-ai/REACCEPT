public byte[] getBytes() {
    byte[] bytes = new byte[SIZE];
    BigendianEncoding.longToByteArray(id, bytes, 0);
    return bytes;
  }