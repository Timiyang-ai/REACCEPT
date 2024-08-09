public static void zipFiles(Collection<File> resFileList, File zipFile, String comment) {
        try {
            for (File resFile : resFileList) {
                zipFile(resFile, "", zipFile, comment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }