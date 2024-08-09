public static List<File> unzipFileByKeyword(final File zipFile,
                                                final File destDir,
                                                final String keyword)
            throws IOException {
        if (zipFile == null || destDir == null) return null;
        List<File> files = new ArrayList<>();
        ZipFile zip = new ZipFile(zipFile);
        Enumeration<?> entries = zip.entries();
        try {
            if (isSpace(keyword)) {
                while (entries.hasMoreElements()) {
                    ZipEntry entry = ((ZipEntry) entries.nextElement());
                    String entryName = entry.getName();
                    if (entryName.contains("../")) {
                        Log.e("ZipUtils", "it's dangerous!");
                        return files;
                    }
                    if (!unzipChildFile(destDir, files, zip, entry)) return files;
                }
            } else {
                while (entries.hasMoreElements()) {
                    ZipEntry entry = ((ZipEntry) entries.nextElement());
                    String entryName = entry.getName();
                    if (entryName.contains("../")) {
                        Log.e("ZipUtils", "it's dangerous!");
                        return files;
                    }
                    if (entryName.contains(keyword)) {
                        if (!unzipChildFile(destDir, files, zip, entry)) return files;
                    }
                }
            }
        } finally {
            zip.close();
        }
        return files;
    }