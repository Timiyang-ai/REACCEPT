@SuppressWarnings("serial")
    @Test
    public void testInvokeAsync() throws Exception {
        runInAllDataModes(new TestRunnable() {
            @Override public void run() throws Exception {
                final IgniteCache cache = jcache().withKeepBinary();

                Set keys = new LinkedHashSet() {{
                    for (int i = 0; i < CNT; i++)
                        add(key(i));
                }};

                for (final Object key : keys) {
                    Object res = cache.invokeAsync(key, NOOP_ENTRY_PROC).get();

                    assertNull(res);

                    assertNull(cache.getAsync(key).get());
                }

                for (final Object key : keys) {
                    Object res = cache.invokeAsync(key, INC_ENTRY_PROC_BINARY_OBJ, dataMode).get();

                    assertNull(res);

                    assertEquals(value(0), deserializeBinary(cache.getAsync(key).get()));

                    res = cache.invokeAsync(key, INC_ENTRY_PROC_BINARY_OBJ, dataMode).get();

                    assertEquals(value(0), deserializeBinary(res));

                    assertEquals(value(1), deserializeBinary(cache.getAsync(key).get()));

                    assertTrue((Boolean)cache.removeAsync(key).get());
                }

                // TODO IGNITE-2973: should be always false.
                interceptorBinaryObjExp = atomicityMode() == TRANSACTIONAL;

                try {
                    for (final Object key : keys) {
                        Object res = cache.invokeAsync(key, INC_ENTRY_PROC_USER_OBJ, dataMode).get();

                        assertNull(res);

                        assertEquals(value(0), deserializeBinary(cache.getAsync(key).get()));

                        res = cache.invokeAsync(key, INC_ENTRY_PROC_USER_OBJ, dataMode).get();

                        // TODO IGNITE-2953: uncomment the following assert when the issue will be fixed.
//                              assertEquals(value(0), res);

                        assertEquals(value(1), deserializeBinary(cache.getAsync(key).get()));

                        assertTrue((Boolean)cache.removeAsync(key).get());
                    }
                }
                finally {
                    interceptorBinaryObjExp = true;
                }
            }
        }, PLANE_OBJECT, SERIALIZABLE);
    }