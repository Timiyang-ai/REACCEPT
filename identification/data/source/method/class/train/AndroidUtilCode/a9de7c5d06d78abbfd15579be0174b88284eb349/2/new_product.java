public byte[] getBytes(@NonNull final String key, final byte[] defaultValue) {
        final File file = mDiskCacheManager.getFileIfExists(key);
        if (file == null) return defaultValue;
        byte[] data = readFile2Bytes(file);
        if (DiskCacheHelper.isDue(data)) {
            mDiskCacheManager.removeByKey(key);
            return defaultValue;
        }
        mDiskCacheManager.updateModify(file);
        return DiskCacheHelper.getDataWithoutDueTime(data);
    }