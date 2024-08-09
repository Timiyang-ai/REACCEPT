public static int getSampleNumbers(int numPartitions, long totalNumberOfRecords, int givenSampleNumbers) {
    	if(givenSampleNumbers > 0)
    	{
    		if (givenSampleNumbers > totalNumberOfRecords) {
    			throw new IllegalArgumentException("Number of samples cannot be larger than total number of records.");
			}
    		return givenSampleNumbers;
    	}

    	// Make sure that number of records >= 2 * number of partitions
		if (totalNumberOfRecords < 2 * numPartitions) {
    		throw new IllegalArgumentException("Number of partitions cannot be larger than half of total number of records.");
		}

		if (totalNumberOfRecords < 1000) {
			return (int) totalNumberOfRecords;
		}

		final int minSampleCnt = numPartitions * 2;
    	return (int) Math.max(minSampleCnt, Math.min(totalNumberOfRecords / 100, Integer.MAX_VALUE));
	}