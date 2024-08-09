@Test
	public void testGeoRemove() {

		K key = keyFactory.instance();
		M member1 = valueFactory.instance();

		geoOperations.geoAdd(key, POINT_PALERMO, member1);

		assertThat(geoOperations.geoRemove(key, member1), is(1L));
	}