	public <T> Classification<T> compute(CLAClassifier classifier, int recordNum, int[] pattern,
		int bucket, Object value) {
		
		Map<String, Object> classification = new LinkedHashMap<String, Object>();
		classification.put("bucketIdx", bucket);
		classification.put("actValue", value);
		return classifier.compute(recordNum, classification, pattern, true, true);
	}