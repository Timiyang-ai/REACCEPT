@Test
    @Ignore
    public void testOpenCommPort() throws Exception {
        System.out.println("openCommPort");
        String port = "";
        int portRate = 0;

        instance.openCommAfterEvent();
        expect(expectLastCall()).once();
        mockMessageService.dispatchMessage(anyObject(), anyString());
        expect(expectLastCall()).once();
        mockCommunicator.connect(ConnectionDriver.JSSC, port, portRate);
        replay(instance, mockCommunicator, mockListener);

        Boolean expResult = true;
        Boolean result = instance.openCommPort(getSettings().getConnectionDriver(), port, portRate);
        assertEquals(expResult, result);

        boolean threw = false;
        try {
            instance.openCommPort(getSettings().getConnectionDriver(), port, portRate);
        } catch (Exception ignored) {
            threw = true;
        }
        assertEquals("Cannot open a comm port twice.", true, threw);
        
        verify(instance, mockCommunicator, mockListener);
    }