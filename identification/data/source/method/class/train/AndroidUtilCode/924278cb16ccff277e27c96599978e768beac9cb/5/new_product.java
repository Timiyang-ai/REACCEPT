public boolean remove(@NonNull final String key) {
        DiskCacheManager diskCacheManager = getDiskCacheManager();
        if (diskCacheManager == null) return true;
        return diskCacheManager.removeByKey(key);
    }