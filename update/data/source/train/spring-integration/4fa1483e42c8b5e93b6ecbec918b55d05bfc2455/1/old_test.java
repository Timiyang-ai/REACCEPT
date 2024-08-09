@Test
	public void testFromMessage() throws Exception {
		String s = "test";
		Message<String> message = MessageBuilder.withPayload(s).build();
		SocketMessageMapper mapper = new SocketMessageMapper();
		byte[] bArray = mapper.fromMessage(message);
		assertEquals(s, new String(bArray));
		
	}