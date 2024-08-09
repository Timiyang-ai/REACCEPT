public byte[] getBytes(@NonNull final String key, final byte[] defaultValue) {
        DiskCacheManager diskCacheManager = getDiskCacheManager();
        if (diskCacheManager == null) return defaultValue;
        final File file = diskCacheManager.getFileIfExists(key);
        if (file == null) return defaultValue;
        byte[] data = readFile2Bytes(file);
        if (DiskCacheHelper.isDue(data)) {
            diskCacheManager.removeByKey(key);
            return defaultValue;
        }
        diskCacheManager.updateModify(file);
        return DiskCacheHelper.getDataWithoutDueTime(data);
    }