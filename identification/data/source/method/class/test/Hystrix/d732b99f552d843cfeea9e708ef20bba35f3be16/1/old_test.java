    private void assertNonBlockingQueue(TestHystrixCommand<Integer> command, Action1<TestHystrixCommand<Integer>> assertion, boolean isSuccess, boolean failFast) {
        System.out.println("Running command.queue(), sleeping the test thread until command is complete, and then running assertions...");
        Future<Integer> f = null;
        if (failFast) {
            try {
                f = command.queue();
                fail("Expected a failure when queuing the command");
            } catch (Exception ex) {
                System.out.println("Received expected fail fast ex : " + ex);
                ex.printStackTrace();
            }
        } else {
            try {
                f = command.queue();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        awaitCommandCompletion(command);

        assertion.call(command);

        if (isSuccess) {
            try {
                f.get();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else {
            try {
                f.get();
                fail("Expected a command failure!");
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            } catch (ExecutionException ee) {
                System.out.println("Received expected ex : " + ee.getCause());
                ee.getCause().printStackTrace();
            } catch (Exception e) {
                System.out.println("Received expected ex : " + e);
                e.printStackTrace();
            }
        }
    }