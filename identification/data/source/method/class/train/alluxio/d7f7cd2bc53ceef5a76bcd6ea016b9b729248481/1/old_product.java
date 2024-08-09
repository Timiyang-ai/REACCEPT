public static void cleanDirectBuffer(ByteBuffer buffer) {
    if (buffer == null) {
      return;
    }
    if (buffer.isDirect()) {
      Cleaner cleaner = ((DirectBuffer) buffer).cleaner();
      cleaner.clean();
    }
  }