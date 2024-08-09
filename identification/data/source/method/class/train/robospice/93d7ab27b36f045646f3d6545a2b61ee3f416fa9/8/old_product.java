public void cancel(final SpiceRequest<?> request) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                request.cancel();
            }
        });
    }