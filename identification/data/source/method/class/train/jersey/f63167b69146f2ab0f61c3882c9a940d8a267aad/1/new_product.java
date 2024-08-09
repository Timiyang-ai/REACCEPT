@GET
        @Path("executorAsync")
        @ManagedAsync
        public void executorAsync(@Suspended final AsyncResponse asyncResponse) {
            LocationHeaderTest.executor.submit(new Runnable() {
                @Override
                public void run() {
                    final URI uri = getUriBuilder().segment("executorAsync").build();
                    final Response result = Response.created(uri).build();
                    asyncResponse.resume(result);
                    if (!uriInfo.getAbsolutePath().equals(result.getLocation())) {
                        executorComparisonFailed.set(true);
                    }
                }
            });
        }