public void updateState(List<TridentTuple> tuples) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for (TridentTuple tuple : tuples) {
            String source = tupleMapper.getSource(tuple);
            String index = tupleMapper.getIndex(tuple);
            String type = tupleMapper.getType(tuple);
            String id = tupleMapper.getId(tuple);

            bulkRequest.add(client.prepareIndex(index, type, id).setSource(source));
        }
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            LOG.warn("failed processing bulk index requests " + bulkResponse.buildFailureMessage());
            throw new FailedException();
        }
    }