public synchronized void setCapacity(int capacity) {
		LRUCache<T, Bitmap> lruCacheNew = new LRUCache<T, Bitmap>(capacity);
		lruCacheNew.putAll(this.lruCache);
		this.lruCache = lruCacheNew;
	}