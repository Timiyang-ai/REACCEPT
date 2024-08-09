public static boolean zipFiles(final Collection<String> resFilePaths,
                                   final String zipFilePath,
                                   final String comment)
            throws IOException {
        if (resFilePaths == null || zipFilePath == null) return false;
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFilePath));
            for (String resFile : resFilePaths) {
                if (!zipFile(getFileByPath(resFile), "", zos, comment)) return false;
            }
            return true;
        } finally {
            if (zos != null) {
                zos.finish();
                CloseUtils.closeIO(zos);
            }
        }
    }