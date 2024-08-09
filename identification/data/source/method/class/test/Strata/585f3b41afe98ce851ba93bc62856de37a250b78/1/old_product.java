public static void unpackInMemory(BeanByteSource source, BiConsumer<String, ArrayByteSource> consumer) {
    String fileName = source.getFileName().orElse("");
    if (suffixMatches(fileName, ".zip")) {
      unzip(source, consumer);

    } else if (suffixMatches(fileName, ".gz")) {
      ungz(source, fileName, consumer);

    } else if (suffixMatches(fileName, ".base64")) {
      try (InputStream in = Base64.getDecoder().wrap(source.openBufferedStream())) {
        String shortFileName = fileName.substring(0, fileName.length() - 7);
        ArrayByteSource unbase64 = ArrayByteSource.from(in).withFileName(shortFileName);
        consumer.accept(shortFileName, unbase64);
      } catch (IOException ex) {
        throw new UncheckedIOException(ex);
      }

    } else {
      consumer.accept(fileName, source.load());
    }
  }