@Override
    public void put(CacheKey key, CacheContent content) throws HttpCacheDataStreamException {
        cache.put(key, new MemCachePersistenceObject().buildForCaching(content.getStatus(), content.getCharEncoding(),
                content.getContentType(), content.getHeaders(), content.getInputDataStream(), content.getWriteMethod()));
    }