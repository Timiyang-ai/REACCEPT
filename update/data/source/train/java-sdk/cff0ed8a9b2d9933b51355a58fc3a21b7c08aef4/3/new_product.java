public JobResponse createJob(final String name, final String batchId,
                         final ConversionTarget conversionTarget, final JsonObject config) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("name can not be null or empty");
        if (batchId == null || batchId.isEmpty())
            throw new IllegalArgumentException("batch id can not be null or empty");
        if (conversionTarget == null)
            throw new IllegalArgumentException("conversion target can not be null");

        JsonObject contentJson = new JsonObject();
        contentJson.addProperty("name", name);
        contentJson.addProperty("batch_id", batchId);
        contentJson.addProperty("conversion_target", conversionTarget.toString());
        if (config != null )
            contentJson.add("config", config);

        HttpRequestBase request = Request.Post(ConfigConstants.JOBS_PATH)
                                         .withContent(contentJson).build();

        try {
            HttpResponse response = docConversionService.execute(request);
            String JobAsJson = ResponseUtil.getString(response);
            JobResponse job = GsonSingleton.getGson().fromJson(JobAsJson, JobResponse.class);
            return job;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }