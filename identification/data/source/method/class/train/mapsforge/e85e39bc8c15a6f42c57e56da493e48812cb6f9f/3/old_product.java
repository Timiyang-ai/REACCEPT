public synchronized void setCapacity(int capacity) {
		LRUCache<Job, Bitmap> lruCacheNew = new LRUCache<Job, Bitmap>(capacity);
		lruCacheNew.putAll(this.lruCache);
		this.lruCache = lruCacheNew;
	}