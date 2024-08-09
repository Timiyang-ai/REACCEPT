public static boolean createOrExistsFile(File file) {
        if (file == null) return false;
        if (file.exists() && file.isFile()) return true;
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }