private Message encodeDecode(final Message m1) throws Exception {
        AtomicReference<Message> m2 = new AtomicReference<Message>();
        TomP2POutbound encoder = new TomP2POutbound(true, new DefaultSignatureFactory());
        CompositeByteBuf buf = Unpooled.compositeBuffer();
        ChannelHandlerContext ctx = mockChannelHandlerContext(buf, m2);
        encoder.write(ctx, m1, null);
        Decoder decoder = new Decoder(new DefaultSignatureFactory());
        decoder.decode(ctx, buf, m1.getRecipient().createSocketTCP(), m1.getSender().createSocketTCP());
        return decoder.message();
    }