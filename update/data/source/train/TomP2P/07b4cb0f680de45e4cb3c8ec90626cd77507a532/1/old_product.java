private Message encodeDecode(final Message m1) throws Exception {
		AtomicReference<Message> m2 = new AtomicReference<Message>();
		final AlternativeCompositeByteBuf buf = AlternativeCompositeByteBuf.compBuffer();
		TomP2POutbound encoder = new TomP2POutbound(true,
				new DefaultSignatureFactory(), new CompByteBufAllocator() {
					@Override
					public AlternativeCompositeByteBuf compBuffer() {
						return buf;
					}
					@Override
					public AlternativeCompositeByteBuf compDirectBuffer() {
						return buf;
					}
				});
		
		buf.retain();
		ChannelHandlerContext ctx = mockChannelHandlerContext(buf, m2);
		encoder.write(ctx, m1, null);
		Decoder decoder = new Decoder(new DefaultSignatureFactory());
		decoder.decode(ctx, buf, m1.getRecipient().createSocketTCP(), m1
				.getSender().createSocketTCP());
		return decoder.message();
	}