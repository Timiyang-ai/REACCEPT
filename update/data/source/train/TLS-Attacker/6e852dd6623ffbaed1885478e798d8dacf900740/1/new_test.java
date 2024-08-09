@Test
    public void testAdjustTLSContext() {
        PaddingExtensionMessage msg = new PaddingExtensionMessage();
        msg.setPaddingBytes(new byte[6]);
        handler.adjustTLSContext(msg);
        assertEquals(context.getPaddingExtensionLength(), 6);
    }