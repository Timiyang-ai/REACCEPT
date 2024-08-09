static ReportFiles createReportFiles(
      File destination, Optional<File> entryPointHint, Path rootDir) {

    Path destinationPath = rootDir.relativize(toNormalizedPath(destination));

    if (destination.isFile()) {
      // The destination is a single file - find its root, and add this single file to the
      // ReportFiles.
      return ReportFiles.createSingleFile(destinationPath, toByteArraySupplier(destination));
    }

    if (!destination.isDirectory()) {
      // This isn't a file nor a directory - so it doesn't exist! Return empty ReportFiles
      return ReportFiles.create(ImmutableMap.of(), destinationPath);
    }

    // The destination is a directory - find all the actual files first
    ImmutableMap<Path, Supplier<byte[]>> files =
        Streams.stream(Files.fileTraverser().depthFirstPreOrder(destination))
            .filter(File::isFile)
            .collect(
                toImmutableMap(
                    file -> rootDir.relativize(toNormalizedPath(file)),
                    file -> toByteArraySupplier(file)));

    if (files.isEmpty()) {
      // The directory exists, but is empty. Return empty ReportFiles
      return ReportFiles.create(ImmutableMap.of(), destinationPath);
    }

    if (files.size() == 1) {
      // We got a directory, but it only has a single file. We can link to that.
      return ReportFiles.create(files, getOnlyElement(files.keySet()));
    }

    // There are multiple files in the report! We need to check the entryPointHint
    Optional<Path> entryPointPath =
        entryPointHint.map(file -> rootDir.relativize(toNormalizedPath(file)));

    if (entryPointPath.isPresent() && files.containsKey(entryPointPath.get())) {
      // We were given the entry point! Use it!
      return ReportFiles.create(files, entryPointPath.get());
    }

    // We weren't given an appropriate entry point. But we still need a single link to all this data
    // - so we'll zip it and just host a single file.
    Path zipFilePath = destinationPath.resolve(destinationPath.getFileName().toString() + ".zip");
    return ReportFiles.createSingleFile(zipFilePath, createZippedByteArraySupplier(files));
  }