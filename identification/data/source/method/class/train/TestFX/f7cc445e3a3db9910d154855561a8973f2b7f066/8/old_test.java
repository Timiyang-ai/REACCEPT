    @Test
    public void clearExceptionsTest() throws Throwable {
        // given:
        WaitForAsyncUtils.printException = false;
        Callable<Void> callable = () -> {
            throw new UnsupportedOperationException();
        };
        WaitForAsyncUtils.clearExceptions();

        // when:
        Future<Void> future = WaitForAsyncUtils.async(callable);

        // then:
        waitForException(future);
        WaitForAsyncUtils.clearExceptions();
        WaitForAsyncUtils.checkException();
        waitForThreads(future);
    }