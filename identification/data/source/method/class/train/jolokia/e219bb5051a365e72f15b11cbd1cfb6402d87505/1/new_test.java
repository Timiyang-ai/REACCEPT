@Test
    public void handleAllServersAtOnceTest() throws MalformedObjectNameException {
        JmxReadRequest request = new JmxRequestBuilder(READ, testBeanName).
                attribute("attr").
                build();
        assertFalse(handler.handleAllServersAtOnce(request));
        request = new JmxRequestBuilder(READ, testBeanName).
                attributes(Arrays.asList("attr1","attr2")).
                build();
        assertTrue(handler.handleAllServersAtOnce(request));
        request = new JmxRequestBuilder(READ, testBeanName).
                attributes((List) null).
                build();
        assertTrue(handler.handleAllServersAtOnce(request));
        request = new JmxRequestBuilder(READ, "java.lang:*").
                attribute("attr").
                build();
        assertTrue(handler.handleAllServersAtOnce(request));
    }