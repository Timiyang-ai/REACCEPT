    public void test_remove() {
        MapDefaultMethodTester
                .test_remove(new ConcurrentHashMap<>(), false /*doesNotAcceptNullKey*/,
                        false /*doesNotAcceptNullValue*/);
    }