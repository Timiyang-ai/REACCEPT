@Test
	public void testToMessage() throws Exception {
		SocketMessageMapper mapper = new SocketMessageMapper();
		Message<Object> message = mapper.toMessage(new StubSocketReader());
		assertEquals(TEST_PAYLOAD, new String((byte[]) message.getPayload()));
		assertEquals(InetAddress.getLocalHost().getHostName(), message
				.getHeaders().get(IpHeaders.HOSTNAME));
		assertEquals(InetAddress.getLocalHost().getHostAddress(), message
				.getHeaders().get(IpHeaders.IP_ADDRESS));
		assertEquals(0, message
				.getHeaders().get(IpHeaders.REMOTE_PORT));
	}