@GET
   @QueryParams(keys = "command", values = "listAsyncJobs")
   @ResponseParser(ParseAsyncJobsFromHttpResponse.class)
   @ExceptionParser(ReturnEmptySetOnNotFoundOr404.class)
   ListenableFuture<Set<AsyncJob<?>>> listAsyncJobs(ListAsyncJobsOptions... options);