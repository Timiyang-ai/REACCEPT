@Test
	public void testExpire() throws Exception {

		populateBoundKey();

		assertEquals(keyOps.getClass().getName() + " -> " + keyOps.getKey(), Long.valueOf(-1), keyOps.getExpire());
		if (keyOps.expire(10, TimeUnit.SECONDS)) {
			long expire = keyOps.getExpire().longValue();
			assertTrue(expire <= 10 && expire > 5);
		}
	}