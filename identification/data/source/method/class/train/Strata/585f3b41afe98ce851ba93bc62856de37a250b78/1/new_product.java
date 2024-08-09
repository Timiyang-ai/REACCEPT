public static void unzip(BeanByteSource source, Path path) {
    Set<ZipKey> deduplicate = new HashSet<>();
    try (ZipInputStream in = new ZipInputStream(source.openStream())) {
      ZipEntry entry = in.getNextEntry();
      while (entry != null) {
        if (!entry.isDirectory()) {
          if (deduplicate.add(new ZipKey(entry))) {
            Path resolved = path.resolve(entry.getName());
            Files.createDirectories(resolved);
            Files.copy(in, resolved, StandardCopyOption.REPLACE_EXISTING);
          }
        }
        in.closeEntry();
        entry = in.getNextEntry();
      }
    } catch (IOException ex) {
      throw new UncheckedIOException(ex);
    }
  }