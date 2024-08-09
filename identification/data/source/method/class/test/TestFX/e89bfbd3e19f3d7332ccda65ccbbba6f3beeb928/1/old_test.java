    @Test
    public void async_callable() throws Exception {
        // when:
        Future<String> future = WaitForAsyncUtils.async(() -> "foo");

        // then:
        Thread.sleep(10);
        assertThat(future.get(), CoreMatchers.is("foo"));
        waitForThreads(future);
    }