public static ArrayByteSource from(ByteSource other) {
    if (other instanceof ArrayByteSource) {
      return (ArrayByteSource) other;
    }
    String fileName = null;
    if (other instanceof BeanByteSource) {
      fileName = ((BeanByteSource) other).getFileName().orElse(null);
    } else {
      String str = other.toString();
      if (str.startsWith("Files.asByteSource(")) {
        int pos = str.indexOf(')', 19);
        fileName = Paths.get(str.substring(19, pos)).getFileName().toString();
      } else if (str.startsWith("Resources.asByteSource(")) {
        int pos = str.indexOf(')', 23);
        String path = str.substring(23, pos);
        int lastSlash = path.lastIndexOf('/');
        fileName = path.substring(lastSlash + 1);
      }
    }
    return new ArrayByteSource(Unchecked.wrap(() -> other.read()), fileName);
  }