public Job createJob(final String label, final String[] seeds) {
    if (dataset == null)
      throw new IllegalArgumentException("dataset cannot be null or empty");
    if (label == null)
      throw new IllegalArgumentException("label cannot be null or empty");
    if (seeds == null || seeds.length == 0)
      throw new IllegalArgumentException("seeds cannot be null or empty");

    final JsonArray seedJsonArray = new JsonArray();
    for (final String seed : seeds) {
      seedJsonArray.add(new JsonPrimitive(seed));
    }

    final JsonObject payload = new JsonObject();
    payload.addProperty(LABEL, label);
    payload.addProperty(DATASET, dataset.getId());
    payload.add(SEEDS, seedJsonArray);

    final Request request = RequestBuilder.post("/v1/upload").withBodyJson(payload).build();
    return executeRequest(request, Job.class);
  }