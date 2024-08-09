private <T> void assertNonBlockingQueue(TestHystrixCommand<T> command, Action1<TestHystrixCommand<T>> assertion, boolean isSuccess) {
            System.out.println("Running command.queue(), sleeping the test thread until command is complete, and then running assertions...");
            Future<T> f = command.queue();
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