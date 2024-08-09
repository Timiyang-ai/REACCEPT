public static ArrayByteSource from(ByteSource other) {
    if (other instanceof ArrayByteSource) {
      return (ArrayByteSource) other;
    }
    return new ArrayByteSource(Unchecked.wrap(() -> other.read()));
  }