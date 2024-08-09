public synchronized void setCapacity(int capacity) {
		BitmapLRUCache lruCacheNew = new BitmapLRUCache(capacity);
		lruCacheNew.putAll(this.lruCache);
		this.lruCache = lruCacheNew;
	}