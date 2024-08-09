public InternalRunner create(Class<?> klass) throws InvocationTargetException {
        return create(klass, new Supplier<MockitoTestListener>() {
            public MockitoTestListener get() {
                return new NoOpTestListener();
            }
        });
    }