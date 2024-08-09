@VisibleForTesting
    String getDynamicTaskName(Map<String, Object> taskInput, String taskNameParam) throws TerminateWorkflow {
        return Optional.ofNullable(taskInput.get(taskNameParam))
                .map(String::valueOf)
                .orElseThrow(() -> {
                    String reason = String.format("Cannot map a dynamic task based on the parameter and input. " +
                            "Parameter= %s, input= %s", taskNameParam, taskInput);
                    return new TerminateWorkflow(reason);
                });
    }