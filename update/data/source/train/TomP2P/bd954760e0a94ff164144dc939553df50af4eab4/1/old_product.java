private Message2 encodeDecode(final Message2 m1) throws Exception {
        AtomicReference<Message2> m2 = new AtomicReference<Message2>();
        TomP2POutbound encoder = new TomP2POutbound(true, new DefaultSignatureFactory());
        ByteBuf buf = Unpooled.buffer();
        ChannelHandlerContext ctx = mockChannelHandlerContext(buf, m2);
        encoder.write(ctx, m1, null);
        TomP2PDecoder decoder = new TomP2PDecoder(new DefaultSignatureFactory());
        decoder.decode(ctx, buf, m1.getRecipient().createSocketTCP(), m1.getSender().createSocketTCP());
        return m2.get();
    }