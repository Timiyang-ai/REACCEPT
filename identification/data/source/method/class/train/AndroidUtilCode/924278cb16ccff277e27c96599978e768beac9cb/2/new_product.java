public int getCacheCount() {
        DiskCacheManager diskCacheManager = getDiskCacheManager();
        if (diskCacheManager == null) return 0;
        return diskCacheManager.getCacheCount();
    }