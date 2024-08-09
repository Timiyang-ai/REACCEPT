public static boolean createOrExistsDir(File file) {
        return file != null && (file.exists() && file.isDirectory() || file.mkdirs());
    }