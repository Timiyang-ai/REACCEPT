ListMultimap<Long, DataPointsRowKey> getKeysForQuery(DatastoreMetricQuery query)
	{
		ListMultimap<Long, DataPointsRowKey> retMap = ArrayListMultimap.create();

		//List<DataPointsRowKey> ret = new ArrayList<DataPointsRowKey>();

		SliceQuery<String, DataPointsRowKey, String> sliceQuery =
				HFactory.createSliceQuery(m_keyspace, StringSerializer.get(),
						DATA_POINTS_ROW_KEY_SERIALIZER, StringSerializer.get());

		DataPointsRowKey startKey = new DataPointsRowKey(query.getName(),
				calculateRowTime(query.getStartTime()));

		/*
		Adding 1 to the end time ensures we get all the keys that have end time and
		have tags in the key.
		 */
		DataPointsRowKey endKey = new DataPointsRowKey(query.getName(),
				calculateRowTime(query.getEndTime()) + 1);


		sliceQuery.setColumnFamily(CF_ROW_KEY_INDEX)
				.setKey(query.getName());

		ColumnSliceIterator<String, DataPointsRowKey, String> iterator =
				new ColumnSliceIterator<String, DataPointsRowKey, String>(sliceQuery,
						startKey, endKey, false, m_singleRowReadSize);

		SetMultimap<String, String> filterTags = query.getTags();
		outer: while (iterator.hasNext())
		{
			DataPointsRowKey rowKey = iterator.next().getName();

			Map<String, String> keyTags = rowKey.getTags();
			for (String tag : filterTags.keySet())
			{
				String value = keyTags.get(tag);
				if (value == null || !filterTags.get(tag).contains(value))
					continue outer; //Don't want this key
			}

			retMap.put(rowKey.getTimestamp(), rowKey);
			//ret.add(rowKey);
		}

		if (logger.isDebugEnabled())
			logger.debug("Querying the database using " + retMap.size() + " keys");

		return (retMap);
	}