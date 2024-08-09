@GET
   @QueryParams(keys = { "command", "listAll" }, values = { "listAsyncJobs", "true" })
   @ResponseParser(ParseAsyncJobsFromHttpResponse.class)
   @ExceptionParser(ReturnEmptySetOnNotFoundOr404.class)
   ListenableFuture<Set<AsyncJob<?>>> listAsyncJobs(ListAsyncJobsOptions... options);