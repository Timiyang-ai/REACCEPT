List<String> timeoutRecords(Long currentTime) {

		if (timeOfLastUpdate + TIMEOUT < currentTime) {

			List<String> timedOutRecords = new LinkedList<String>();
			Map<Long, Set<String>> timedOut = recordsByTime.subMap(0L, currentTime
					- TIMEOUT);

			for (Set<String> recordSet : timedOut.values()) {
				if (!recordSet.isEmpty()) {
					for (String recordID : recordSet) {
						timedOutRecords.add(recordID);
					}
				}
			}

			recordsByTime.keySet().removeAll(timedOut.keySet());
			for (String recordID : timedOutRecords) {
				failRecord(recordID);
			}

			timeOfLastUpdate = currentTime;
			return timedOutRecords;
		}

		return null;
	}