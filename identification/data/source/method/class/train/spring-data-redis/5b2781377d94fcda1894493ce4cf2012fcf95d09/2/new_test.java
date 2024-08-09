@Test
	public void testPersist() throws Exception {
		assumeTrue(!ConnectionUtils.isJredis(template.getConnectionFactory()));

		keyOps.persist();

		assertEquals(keyOps.getClass().getName() + " -> " + keyOps.getKey(), Long.valueOf(-1), keyOps.getExpire());
		if (keyOps.expire(10, TimeUnit.SECONDS)) {
			assertTrue(keyOps.getExpire().longValue() > 0);
		}

		keyOps.persist();
		assertEquals(keyOps.getClass().getName() + " -> " + keyOps.getKey(), -1, keyOps.getExpire().longValue());
	}