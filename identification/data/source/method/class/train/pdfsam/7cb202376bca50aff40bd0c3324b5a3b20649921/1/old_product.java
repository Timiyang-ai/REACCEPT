@EventListener(priority = Integer.MAX_VALUE)
    public void request(TaskExecutionRequestEvent event) {
        LOG.trace("Task execution request received");
        usageService.incrementUsageFor(event.getModuleId());
        executor.execute(() -> executionService.submit(event.getModuleId(), event.getParameters()));
        LOG.trace("Task execution submitted");
    }