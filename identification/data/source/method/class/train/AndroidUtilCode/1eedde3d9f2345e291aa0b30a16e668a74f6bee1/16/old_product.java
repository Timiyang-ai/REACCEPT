public static void zipFiles(Collection<File> resFiles, File zipFile, String comment)
            throws IOException {
        if (resFiles == null || zipFile == null) return;
        zipFiles(resFiles, new ZipOutputStream(new FileOutputStream(zipFile)), comment);
    }