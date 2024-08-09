@Test
    public void testInitializeUnchecked() throws ConcurrentException {
        @SuppressWarnings("unchecked")
        final
        ConcurrentInitializer<Object> init = EasyMock
                .createMock(ConcurrentInitializer.class);
        final Object result = new Object();
        EasyMock.expect(init.get()).andReturn(result);
        EasyMock.replay(init);
        assertSame("Wrong result object", result, ConcurrentUtils
                .initializeUnchecked(init));
        EasyMock.verify(init);
    }