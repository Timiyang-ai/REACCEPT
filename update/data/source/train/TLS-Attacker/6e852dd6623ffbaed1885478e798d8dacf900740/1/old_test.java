@Test
    public void testAdjustTLSContext() {
        PaddingExtensionMessage msg = new PaddingExtensionMessage();
        msg.setPaddingLength(6);
        handler.adjustTLSContext(msg);
        assertEquals(context.getPaddingExtensionLength(), 6);
    }