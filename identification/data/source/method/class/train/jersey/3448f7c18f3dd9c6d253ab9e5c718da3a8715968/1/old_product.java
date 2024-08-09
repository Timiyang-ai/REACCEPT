@GET
    @Produces("text/plain")
    @Suspend
    public void get() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    context.resume(HELLO_ASYNC_WORLD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }