public static <T> byte[] write(WriteBuffer.Writer<T> writer, T value) {
    byte[] result = new byte[writer.sizeInBytes(value)];
    WriteBuffer b = WriteBuffer.wrap(result);
    try {
      writer.write(value, b);
    } catch (RuntimeException e) {
      int lengthWritten = result.length;
      for (int i = 0; i < result.length; i++) {
        if (result[i] == 0) {
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
          result.length,
          new String(result, 0, lengthWritten, UTF_8));
      throw Platform.get().assertionError(message, e);
    }
    return result;
  }