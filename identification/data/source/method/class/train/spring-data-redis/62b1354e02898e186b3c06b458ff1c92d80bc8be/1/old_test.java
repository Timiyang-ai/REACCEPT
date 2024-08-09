@Test
	public void rename() {

		nativeConnection.set(KEY_1, VALUE_1);

		clusterConnection.rename(KEY_1_BYTES, KEY_2_BYTES);

		assertThat(nativeConnection.exists(KEY_1), is(false));
		assertThat(nativeConnection.get(KEY_2), is(VALUE_1));
	}