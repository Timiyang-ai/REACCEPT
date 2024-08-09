@Test
    public void testInitializeUnchecked() throws ConcurrentException {
        @SuppressWarnings("unchecked")
        ConcurrentInitializer<Object> init = EasyMock
                .createMock(ConcurrentInitializer.class);
        final Object result = new Object();
        EasyMock.expect(init.get()).andReturn(result);
        EasyMock.replay(init);
        Object testResult = ConcurrentUtils.initializeUnchecked(init);
        assertSame("Wrong result object", result, testResult);
        EasyMock.verify(init);
    }