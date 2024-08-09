private Message encodeDecode(final Message m1) throws Exception {
		AtomicReference<Message> m2 = new AtomicReference<Message>();
		final CompositeByteBuf buf = Unpooled.compositeBuffer();
		Encoder encoder = new Encoder(new DSASignatureFactory());
		encoder.write(buf, m1, null);
		ChannelHandlerContext ctx = mockChannelHandlerContext(buf, m2);
		Decoder decoder = new Decoder(new DSASignatureFactory());
		decoder.decode(ctx, buf, m1.recipient().ipv4Socket().createTCPSocket(), m1
				.sender().ipv4Socket().createTCPSocket());
		//buf.release();
		return decoder.message();
	}