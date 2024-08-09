@Test
    public void testRun_exception() throws Exception {
        expect(channel.close()).andReturn(null);
        control.replay();
        
        handler.exceptionCaught(ctx,
                new DefaultExceptionEvent(channel, new IOException("proto")));

        control.verify();
    }