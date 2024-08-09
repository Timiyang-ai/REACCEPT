public static <T> byte[] write(UnsafeBuffer.Writer<T> writer, T value) {
    UnsafeBuffer b = UnsafeBuffer.allocate(writer.sizeInBytes(value));
    try {
      writer.write(value, b);
    } catch (RuntimeException e) {
      byte[] bytes = b.unwrap();
      int lengthWritten = bytes.length;
      for (int i = 0; i < bytes.length; i++) {
        if (bytes[i] == 0) {
          lengthWritten = i;
          break;
        }
      }

      // Don't use value directly in the message, as its toString might be implemented using this
      // method. If that's the case, we'd stack overflow. Instead, emit what we've written so far.
      String message =
        format(
          "Bug found using %s to write %s as json. Wrote %s/%s bytes: %s",
          writer.getClass().getSimpleName(),
          value.getClass().getSimpleName(),
          lengthWritten,
          bytes.length,
          new String(bytes, 0, lengthWritten, UTF_8));
      throw Platform.get().assertionError(message, e);
    }
    return b.unwrap();
  }