public boolean addEvent(@NonNull final HUTraceEvent huTraceEvent)
	{
		final HUTraceEventQuery query = huTraceEvent.asQuery();

		final I_M_HU_Trace dbRecord;
		final List<I_M_HU_Trace> existingDbRecords = queryDbRecord(query);
		final boolean inserted = existingDbRecords.isEmpty();
		if (inserted)
		{
			dbRecord = newInstance(I_M_HU_Trace.class);
			logger.info("Found no existing M_HU_Trace record; creating new one; query={}", query);

			copyToDbRecord(huTraceEvent, dbRecord);
			save(dbRecord);
		}
		else
		{
			Check.errorIf(existingDbRecords.size() > 1,
					"Expected only one M_HU_Trace record for the given query, but found {}; query={}, M_HU_Trace records={}",
					existingDbRecords.size(), query, existingDbRecords);

			dbRecord = existingDbRecords.get(0);
			logger.info("Found exiting M_HU_Trace record; nothing to do; query={}; record={}", query, dbRecord);
		}

		return inserted;
	}