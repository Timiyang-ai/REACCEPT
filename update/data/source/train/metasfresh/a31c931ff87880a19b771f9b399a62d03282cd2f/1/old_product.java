public void addEvent(@NonNull final HUTraceEvent huTraceEvent)
	{
		final HUTraceSpecification query = HUTraceSpecification.builder()
				.huId(huTraceEvent.getHuId())
				.eventTime(huTraceEvent.getEventTime())
				.recursionMode(RecursionMode.NONE)
				.build();

		final I_M_HU_Trace dbRecord;
		final List<I_M_HU_Trace> existingDbRecords = queryDbRecord(query);
		if (existingDbRecords.isEmpty())
		{
			dbRecord = newInstance(I_M_HU_Trace.class);
		}
		else
		{
			Check.errorIf(existingDbRecords.size() > 1,
					"Expected only one M_HU_Trace record for the given query, but found {}; query={}, M_HU_Trace records={}",
					existingDbRecords.size(), query, existingDbRecords);
			dbRecord = existingDbRecords.get(0);
		}
		copyToDbRecord(huTraceEvent, dbRecord);
		save(dbRecord);
	}