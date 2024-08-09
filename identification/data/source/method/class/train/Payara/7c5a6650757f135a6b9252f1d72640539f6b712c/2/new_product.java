void getListOfFiles(File directory, List<String> files, List embeddedArchives, final Logger logger) {
        if(archive == null || directory == null || !directory.isDirectory())
            return;
        try {
            Path archiveRoot = archive.toPath();
            Path root = directory.toPath();
            int embeddedLimit = root.getNameCount()+1;
            Files.walkFileTree(root, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    if (dir.equals(root)) {
                        return FileVisitResult.CONTINUE;
                    }
                    String fileName = getFilename(dir);
                    if (isEntryValid(fileName, logger)) {
                        files.add(fileName); // Add entry corresponding to the directory also to the list
                        // embedded archives are only checked at first level
                        if (embeddedArchives != null && dir.getNameCount() <= embeddedLimit
                                && embeddedArchives.contains(fileName)) {
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        return FileVisitResult.CONTINUE;
                    } else {
                        return FileVisitResult.SKIP_SUBTREE;
                    }
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileName = getFilename(file);
                    if (!fileName.equals(JarFile.MANIFEST_NAME) && isEntryValid(fileName, logger)) {
                        files.add(fileName);
                    }
                    return FileVisitResult.CONTINUE;
                }

                private String getFilename(Path file) {
                    String fileName = archiveRoot.relativize(file).toString();
                    fileName = fileName.replace(File.separatorChar, '/');
                    return fileName;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    logListFailure(exc, directory);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            logListFailure(e, directory);
        }
    }