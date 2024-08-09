public synchronized void setCapacity(int capacity) {
		if (capacity == this.lruCache.getCapacity()) {
			return;
		}

		LRUCache<T, Bitmap> lruCacheNew = new LRUCache<T, Bitmap>(capacity);
		lruCacheNew.putAll(this.lruCache);
		this.lruCache = lruCacheNew;
	}