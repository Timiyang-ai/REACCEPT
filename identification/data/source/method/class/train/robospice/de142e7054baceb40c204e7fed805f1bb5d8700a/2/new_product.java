public <T> void addListenerIfPending(final Class<T> clazz,
                        final String requestCacheKey,
                        final RequestListener<T> requestListener) {
                final SpiceRequest<T> request = new SpiceRequest<T>(clazz) {

                        @Override
                        public T loadDataFromNetwork() throws Exception {
                                return null;
                        }
                };
                final CachedSpiceRequest<T> cachedSpiceRequest = new CachedSpiceRequest<T>(
                                request, requestCacheKey,
                                DurationInMillis.NEVER);
                cachedSpiceRequest.setProcessable(false);
                execute(cachedSpiceRequest, requestListener);
        }