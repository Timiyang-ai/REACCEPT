    @Test
    public void executeBySingle() throws Exception {
        asyncTest(10, new TestRunnable<String>() {
            @Override
            public void run(final int index, CountDownLatch latch) {
                final TestTask<String> task = new TestTask<String>(latch) {
                    @Override
                    public String doInBackground() throws Throwable {
                        Thread.sleep(200);
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
                ThreadUtils.executeBySingle(task);
            }
        });
    }