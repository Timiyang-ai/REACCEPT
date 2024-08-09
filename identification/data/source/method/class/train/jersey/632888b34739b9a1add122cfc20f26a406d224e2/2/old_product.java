@GET
        @Path("responseFilterAsyncExecutor")
        public void responseFilterAsyncExecutor(@Suspended final AsyncResponse asyncResponse) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    final Response result =
                            Response.created(getUriBuilder().segment("responseFilterAsyncExecutor").build()).build();
                    asyncResponse.resume(result);
                }
            });
        }