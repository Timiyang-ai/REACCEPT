Supplier<ServerCache> getCache(Class<?> beanType, String cacheKey, ServerCacheType type) {

    if (tenantProvider == null) {
      return new SimpleSupplier(getCacheInternal(beanType, cacheKey, type));
    }
    return new TenantSupplier(beanType, cacheKey, type);
  }