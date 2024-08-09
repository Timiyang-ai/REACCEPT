public static boolean writeFileFromBytesByStream(String filePath, final byte[] bytes) {
        return writeFileFromBytesByStream(FileUtils.getFileByPath(filePath), bytes, false);
    }