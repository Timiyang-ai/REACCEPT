@Test
    @Ignore
    public void testCloseCommPort() throws Exception {
        System.out.println("closeCommPort");

        String port = "/some/port";
        int baud = 1234;

        // Events
        instance.openCommAfterEvent();
        expect(expectLastCall()).once();
        instance.closeCommBeforeEvent();
        expect(expectLastCall()).once();
        instance.closeCommAfterEvent();
        expect(expectLastCall()).once();

        // Message for open and close.
        mockMessageService.dispatchMessage(anyObject(), anyString());
        expect(expectLastCall()).times(2);
        expect(mockCommunicator.openCommPort(ConnectionDriver.JSERIALCOMM, port, baud)).andReturn(true).once();
        mockCommunicator.closeCommPort();
        expect(expectLastCall()).once();
        replay(instance, mockCommunicator, mockListener);

        // Close a closed port.
        Boolean result = instance.closeCommPort();
        assertEquals(true, result);

        // Open port to close it.
        result = instance.openCommPort(getSettings().getConnectionDriver(), port, baud);
        assertEquals(result, result);

        // Close an open port.
        result = instance.closeCommPort();
        assertEquals(true, result);
    }