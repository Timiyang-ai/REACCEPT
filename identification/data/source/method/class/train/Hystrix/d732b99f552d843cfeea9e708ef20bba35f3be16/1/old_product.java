private <T> void assertBlockingQueue(TestHystrixCommand<T> command, Action1<TestHystrixCommand<T>> assertion, boolean isSuccess) {
            System.out.println("Running command.queue(), immediately blocking and then running assertions...");
            if (isSuccess) {
                try {
                    command.queue().get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    command.queue().get();
                    fail("Expected a command failure!");
                } catch (InterruptedException ie) {
                    throw new RuntimeException(ie);
                } catch (ExecutionException ee) {
                    System.out.println("Received expected ex : " + ee.getCause());
                    ee.getCause().printStackTrace();
                }
            }

            assertion.call(command);
        }