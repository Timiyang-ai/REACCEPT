@Test
	public void persistShouldRemoveTTL() {

		nativeConnection.setex(KEY_1_BYTES, 10, VALUE_1_BYTES);

		assertThat(clusterConnection.persist(KEY_1_BYTES), is(Boolean.TRUE));
		assertThat(nativeConnection.ttl(KEY_1_BYTES), is(-1L));
	}