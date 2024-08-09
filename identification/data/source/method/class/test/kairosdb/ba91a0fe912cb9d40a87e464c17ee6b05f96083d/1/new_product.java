public void putDataPoints(DataPointSet dps) throws DatastoreException
	{
		try
		{
			long rowTime = -1L;
			DataPointsRowKey rowKey = null;
			//time the data is written.
			long writeTime = System.currentTimeMillis();

			for (DataPoint dp : dps.getDataPoints())
			{
				if (dp.getTimestamp() < 0)
					throw new DatastoreException("Timestamp must be greater than or equal to zero.");
				long newRowTime = calculateRowTime(dp.getTimestamp());
				if (newRowTime != rowTime)
				{
					rowTime = newRowTime;
					rowKey = new DataPointsRowKey(dps.getName(), rowTime, dps.getDataStoreDataType(),
							dps.getTags());

					long now = System.currentTimeMillis();
					//Write out the row key if it is not cached
					if (!m_rowKeyCache.isCached(rowKey))
						m_rowKeyWriteBuffer.addData(dps.getName(), rowKey, "", now);

					//Write metric name if not in cache
					if (!m_metricNameCache.isCached(dps.getName()))
					{
						m_stringIndexWriteBuffer.addData(ROW_KEY_METRIC_NAMES,
								dps.getName(), "", now);
					}

					//Check tag names and values to write them out
					Map<String, String> tags = dps.getTags();
					for (String tagName : tags.keySet())
					{
						if (!m_tagNameCache.isCached(tagName))
						{
							m_stringIndexWriteBuffer.addData(ROW_KEY_TAG_NAMES,
									tagName, "", now);
						}

						String value = tags.get(tagName);
						if (!m_tagValueCache.isCached(value))
						{
							m_stringIndexWriteBuffer.addData(ROW_KEY_TAG_VALUES,
									value, "", now);
						}
					}
				}

				int columnTime = getColumnName(rowTime, dp.getTimestamp());
				KDataOutput kDataOutput = new KDataOutput();
				dp.writeValueToBuffer(kDataOutput);
				m_dataPointWriteBuffer.addData(rowKey, columnTime,
						kDataOutput.getBytes(), writeTime);

			}
		}
		catch (DatastoreException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new DatastoreException(e);
		}
	}