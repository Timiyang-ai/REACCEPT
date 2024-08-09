List<String> timeoutRecords(Long currentTime) {
		if (timeOfLastUpdate + TIMEOUT < currentTime) {
			log.trace("Updating record buffer");
			List<String> timedOutRecords = new LinkedList<String>();
			Map<Long, Set<String>> timedOut = recordsByTime.subMap(0L,
					currentTime - TIMEOUT);

			for (Set<String> recordSet : timedOut.values()) {
				if (!recordSet.isEmpty()) {
					for (String recordID : recordSet) {
						timedOutRecords.add(recordID);
					}
				}
			}

			for (String recordID : timedOutRecords) {
				failRecord(recordID);
			}

			timedOut.clear();

			timeOfLastUpdate = currentTime;
			return timedOutRecords;
		}
		return null;
	}