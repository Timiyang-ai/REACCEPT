public boolean addEvent(@NonNull final HUTraceEvent huTraceEvent)
	{
		final HUTraceSpecification query = HUTraceSpecification.builder()
				.vhuId(huTraceEvent.getVhuId())
				.eventTime(huTraceEvent.getEventTime())
				.recursionMode(RecursionMode.NONE)
				.build();

		final I_M_HU_Trace dbRecord;
		final List<I_M_HU_Trace> existingDbRecords = queryDbRecord(query);
		final boolean inserted = existingDbRecords.isEmpty();
		if (inserted)
		{
			dbRecord = newInstance(I_M_HU_Trace.class);
			logger.info("Found no existing M_HU_Trace record; creating new one; query={}", query);
		}
		else
		{
			Check.errorIf(existingDbRecords.size() > 1,
					"Expected only one M_HU_Trace record for the given query, but found {}; query={}, M_HU_Trace records={}",
					existingDbRecords.size(), query, existingDbRecords);

			dbRecord = existingDbRecords.get(0);
			logger.info("Found no exiting M_HU_Trace record; updating it; query={}; record={}", query, dbRecord);
		}
		copyToDbRecord(huTraceEvent, dbRecord);
		save(dbRecord);

		return inserted;
	}