private static void unzip(BeanByteSource source, BiConsumer<String, ArrayByteSource> consumer) {
    try (ZipInputStream in = new ZipInputStream(source.openStream())) {
      ZipEntry entry = in.getNextEntry();
      while (entry != null) {
        if (!entry.isDirectory()) {
          ArrayByteSource entrySource = ArrayByteSource.copyOf(ByteStreams.toByteArray(in)).withFileName(entry.getName());
          consumer.accept(entry.getName(), entrySource);
        }
        in.closeEntry();
        entry = in.getNextEntry();
      }
    } catch (IOException ex) {
      throw new UncheckedIOException(ex);
    }
  }