public static void delete(File file) throws IOException {
        if (file.isDirectory()) {
            for (File c : file.listFiles()) {
                delete(c);
            }
        }
        if (!org.apache.commons.io.FileUtils.deleteQuietly(file)) {
            throw new FileNotFoundException("Failed to delete file: " + file);
        }
        /* else {
         //delete on exit was a bad idea. if for some reason the file can't be deleted
         // this will cause a newly constructed file to be deleted and a subsequent run may fail.
         // still not sure why a file fails to be deleted, but can be overwritten... odd.
         file.deleteOnExit();
         }*/
    }