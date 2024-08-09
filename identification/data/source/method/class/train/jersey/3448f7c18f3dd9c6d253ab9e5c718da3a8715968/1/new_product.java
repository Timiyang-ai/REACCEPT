@GET
    @Produces("text/plain")
    public void get(@Suspended final AsyncResponse ar) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    ar.resume(HELLO_ASYNC_WORLD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }