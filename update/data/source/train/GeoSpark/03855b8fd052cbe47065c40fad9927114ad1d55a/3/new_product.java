public static int getSampleNumbers(Integer numPartitions, long totalNumberOfRecords) throws Exception {
    	long sampleNumbers;
    	/*
    	 * If the input RDD is too small, Geospark will use the entire RDD instead of taking samples.
    	 */
    	if(totalNumberOfRecords>=1000)
    	{
    		sampleNumbers = totalNumberOfRecords / 100;
    	}
    	else
    	{
    		sampleNumbers = totalNumberOfRecords;
    	}
    	
		if(sampleNumbers > Integer.MAX_VALUE) {
			sampleNumbers = Integer.MAX_VALUE;
		}
        int result=(int)sampleNumbers;
        // Partition size is too big. Should throw exception for this.
        
        if(sampleNumbers < 2*numPartitions ) {
            throw new Exception("[RDDSampleUtils][getSampleNumbers] Too many RDD partitions. Please make this RDD's partitions less than "+sampleNumbers/2);
        }
        
        return result;

	}