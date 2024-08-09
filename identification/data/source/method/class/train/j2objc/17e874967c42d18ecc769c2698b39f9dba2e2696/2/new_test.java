    public void test_putIfAbsent() {
        MapDefaultMethodTester
                .test_putIfAbsent(new ConcurrentHashMap<>(), false /*doesNotAcceptNullKey*/,
                        false /*doesNotAcceptNullValue*/);
    }