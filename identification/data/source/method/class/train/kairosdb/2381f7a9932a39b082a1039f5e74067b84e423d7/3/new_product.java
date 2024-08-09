Iterator<DataPointsRowKey> getKeysForQueryIterator(DatastoreMetricQuery query)
	{
		return (new FilteredRowKeyIterator(query.getName(), query.getStartTime(),
				query.getEndTime(), query.getTags()));
		/*ListMultimap<Long, DataPointsRowKey> retMap = ArrayListMultimap.create();

		SliceQuery<String, DataPointsRowKey, String> sliceQuery =
				HFactory.createSliceQuery(m_keyspace, StringSerializer.get(),
						DATA_POINTS_ROW_KEY_SERIALIZER, StringSerializer.get());

		DataPointsRowKey startKey = new DataPointsRowKey(query.getName(),
				calculateRowTime(query.getStartTime()));

		DataPointsRowKey endKey = new DataPointsRowKey(query.getName(),
				calculateRowTime(query.getEndTime()) + 1);


		sliceQuery.setColumnFamily(CF_ROW_KEY_INDEX)
				.setKey(query.getName());

		ColumnSliceIterator<String, DataPointsRowKey, String> iterator =
				new ColumnSliceIterator<String, DataPointsRowKey, String>(sliceQuery,
						startKey, endKey, false, m_singleRowReadSize);

		SetMultimap<String, String> filterTags = query.getTags();
		outer:
		while (iterator.hasNext())
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

		return (retMap);*/
	}