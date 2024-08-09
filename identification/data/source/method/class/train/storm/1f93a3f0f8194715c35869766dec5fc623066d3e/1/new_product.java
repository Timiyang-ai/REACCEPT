public void updateState(List<TridentTuple> tuples) {
        try {
            String bulkRequest = buildRequest(tuples);
            Response response = client.performRequest("post", "_bulk", new HashMap<String, String>(), new StringEntity(bulkRequest.toString()));
            BulkIndexResponse bulkResponse = objectMapper.readValue(response.getEntity().getContent(), BulkIndexResponse.class);
            if (bulkResponse.hasErrors()) {
                LOG.warn("failed processing bulk index requests: " + bulkResponse.getFirstError() + ": " + bulkResponse.getFirstResult());
                throw new FailedException();
            }
        } catch (IOException e) {
            LOG.warn("failed processing bulk index requests: " + e.toString());
            throw new FailedException(e);
        }
    }