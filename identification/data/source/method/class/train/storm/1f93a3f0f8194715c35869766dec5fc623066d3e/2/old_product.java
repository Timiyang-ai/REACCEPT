public void updateState(List<TridentTuple> tuples, TridentCollector collector) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for (TridentTuple tuple : tuples) {
            String source = tuple.getStringByField("source");
            String index = tuple.getStringByField("index");
            String type = tuple.getStringByField("type");
            String id = tuple.getStringByField("id");

            bulkRequest.add(client.prepareIndex(index, type, id).setSource(source));
        }
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            LOG.warn("failed processing bulk index requests " + bulkResponse.buildFailureMessage());
            throw new FailedException();
        }
    }