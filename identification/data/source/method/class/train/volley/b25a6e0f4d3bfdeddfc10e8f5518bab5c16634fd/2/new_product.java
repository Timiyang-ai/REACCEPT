@MainThread
    public void setImageUrl(String url, ImageLoader imageLoader) {
        Threads.throwIfNotOnMainThread();
        mUrl = url;
        mImageLoader = imageLoader;
        // The URL has potentially changed. See if we need to load it.
        loadImageIfNecessary(/* isInLayoutPass= */ false);
    }