	@Test
	public void test_getKeysForQuery() throws DatastoreException
	{
		DatastoreMetricQuery query = new DatastoreMetricQueryImpl(ROW_KEY_TEST_METRIC,
				HashMultimap.create(), s_dataPointTime, s_dataPointTime);

		List<DataPointsRowKey> keys = readIterator(s_datastore.getKeysForQueryIterator(query));

		assertEquals(4, keys.size());
	}