public static boolean delete(File file) {
        boolean success = true;
        if (file.isDirectory()) { //some of this may duplicative of deleteQuietly....
            for (File f : file.listFiles()) {
                success &= delete(f);
            }
        }
        if (!org.apache.commons.io.FileUtils.deleteQuietly(file)) {
            success = false;
            final String msg = String.format("Failed to delete file: %s", file.getPath());
            Logger.getLogger(FileUtils.class.getName()).log(Level.FINE, msg);
        }
        return success;
    }