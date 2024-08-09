    @Test
    public void submitAll() throws Exception {
        //when
        jobExecutor.submitAll(job, anotherJob);

        //then
        verify(executorService).invokeAll(asList(job, anotherJob));
    }