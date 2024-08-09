@Override
    public void put(CacheKey key, CacheContent content) throws HttpCacheDataStreamException {
        cache.put(key, new MemCacheValue().buildForCaching(content.getCharEncoding(), content.getContentType(),
                content.getHeaders(), content.getInputDataStream()));

    }