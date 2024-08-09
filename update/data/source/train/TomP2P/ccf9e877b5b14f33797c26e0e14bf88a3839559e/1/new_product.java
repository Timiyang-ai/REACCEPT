private Message encodeDecode(final Message m1) throws Exception {
		final CompositeByteBuf buf = Unpooled.compositeBuffer();
		Encoder encoder = new Encoder(new DSASignatureFactory());
		encoder.write(buf, m1, null);
		Decoder decoder = new Decoder(new DSASignatureFactory());
		decoder.decode(buf, m1.recipient().ipv4Socket().createUDPSocket(), m1
				.sender().ipv4Socket().createUDPSocket());
		return decoder.message();
	}