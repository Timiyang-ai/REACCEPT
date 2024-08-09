public Batch createBatch(final String name, final List<Property> properties) {
        JsonObject contentJson = new JsonObject();
        if (name != null && !name.isEmpty())
            contentJson.addProperty("name", name);
        if(properties != null && !properties.isEmpty())
            contentJson.addProperty("properties", new Gson().toJson(properties));

        HttpRequestBase request = Request.Post("/v1/batches")
                                         .withContent(filterJson(contentJson), MediaType.APPLICATION_JSON).build();
        try {
            HttpResponse response = docConversionService.execute(request);
            String batchAsJson = ResponseUtil.getString(response);
            Batch batch = GsonSingleton.getGson().fromJson(batchAsJson, Batch.class);
            return batch;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }