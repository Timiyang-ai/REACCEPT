public static boolean writeFileFromBytesByStream(String filePath, final byte[] bytes, boolean append) {
        return writeFileFromBytesByStream(FileUtils.getFileByPath(filePath), bytes, append);
    }