    @Test
    public void executeBySingleAtFixRate() throws Exception {
        asyncTest(10, new TestRunnable<String>() {
            @Override
            public void run(final int index, CountDownLatch latch) {
                final TestScheduledTask<String> task = new TestScheduledTask<String>(latch, 3) {
                    @Override
                    public String doInBackground() throws Throwable {
                        Thread.sleep(100 + index * 10);
                        if (index < 4) {
                            return Thread.currentThread() + " :" + index;
                        } else if (index < 7) {
                            cancel();
                            return null;
                        } else {
                            throw new NullPointerException(String.valueOf(index));
                        }
                    }

                    @Override
                    void onTestSuccess(String result) {
                        System.out.println(result);
                    }
                };
                ThreadUtils.executeBySingleAtFixRate(task, 2000 + index * 10, TimeUnit.MILLISECONDS);
            }
        });
    }