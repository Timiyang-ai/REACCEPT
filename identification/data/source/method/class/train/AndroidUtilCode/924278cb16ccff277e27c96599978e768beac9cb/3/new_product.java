public boolean remove(@NonNull final String key) {
        DiskCacheManager diskCacheManager = getDiskCacheManager();
        if (diskCacheManager == null) return true;
        return diskCacheManager.removeByKey(TYPE_BYTE + key)
                && diskCacheManager.removeByKey(TYPE_STRING + key)
                && diskCacheManager.removeByKey(TYPE_JSON_OBJECT + key)
                && diskCacheManager.removeByKey(TYPE_JSON_ARRAY + key)
                && diskCacheManager.removeByKey(TYPE_BITMAP + key)
                && diskCacheManager.removeByKey(TYPE_DRAWABLE + key)
                && diskCacheManager.removeByKey(TYPE_PARCELABLE + key)
                && diskCacheManager.removeByKey(TYPE_SERIALIZABLE + key);
    }