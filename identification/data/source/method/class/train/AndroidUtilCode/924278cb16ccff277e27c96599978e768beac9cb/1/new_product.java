public long getCacheSize() {
        DiskCacheManager diskCacheManager = getDiskCacheManager();
        if (diskCacheManager == null) return 0;
        return diskCacheManager.getCacheSize();
    }