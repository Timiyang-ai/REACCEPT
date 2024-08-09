@Test
    public void testAdjustTLSContext() {
        context.setConnectionEndType(ConnectionEndType.CLIENT);
        context.setTalkingConnectionEndType(ConnectionEndType.SERVER);
        AlertMessage message = new AlertMessage();
        message.setDescription(AlertDescription.ACCESS_DENIED.getValue());
        message.setLevel(AlertLevel.WARNING.getValue());
        handler.adjustTLSContext(message);
        assertFalse(context.isReceivedFatalAlert());
        message.setLevel(AlertLevel.FATAL.getValue());
        handler.adjustTLSContext(message);
        assertTrue(context.isReceivedFatalAlert());
    }