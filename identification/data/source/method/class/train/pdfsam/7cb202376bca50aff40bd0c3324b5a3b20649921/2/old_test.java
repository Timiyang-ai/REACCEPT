    @Test
    public void request() {
        String moduleId = "module";
        AbstractParameters params = mock(AbstractParameters.class);
        victim.request(new TaskExecutionRequestEvent(moduleId, params));
        verify(usageService).incrementUsageFor(moduleId);
        verify(executionService, timeout(1000).times(1)).execute(params);
    }