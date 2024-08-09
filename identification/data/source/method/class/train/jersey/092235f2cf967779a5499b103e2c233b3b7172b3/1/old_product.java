@GET
        @Path("timeout")
        public void asyncGetWithTimeout(@Suspended final AsyncResponse asyncResponse) {
            asyncResponse.setTimeoutHandler(new TimeoutHandler() {

                @Override
                public void handleTimeout(AsyncResponse asyncResponse) {
                    asyncResponse.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                            .entity("Operation time out.").build());
                }
            });
            asyncResponse.setTimeout(3, TimeUnit.SECONDS);

            new Thread(new Runnable() {

                @Override
                public void run() {
                    String result = veryExpensiveOperation();
                    asyncResponse.resume(result);
                }

                private String veryExpensiveOperation() {
                    // ... very expensive operation that typically finishes within 10 seconds, simulated using sleep()
                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        // ignore
                    }
                    return "DONE";
                }
            }).start();
        }