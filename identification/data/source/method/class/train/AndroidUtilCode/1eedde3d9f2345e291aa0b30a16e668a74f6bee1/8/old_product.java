public static boolean zipFiles(final Collection<String> srcFilePaths,
                                   final String zipFilePath,
                                   final String comment)
            throws IOException {
        if (srcFilePaths == null || zipFilePath == null) return false;
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFilePath));
            for (String srcFile : srcFilePaths) {
                if (!zipFile(getFileByPath(srcFile), "", zos, comment)) return false;
            }
            return true;
        } finally {
            if (zos != null) {
                zos.finish();
                CloseUtils.closeIO(zos);
            }
        }
    }