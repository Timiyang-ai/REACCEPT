@VisibleForTesting
    TaskDef getDynamicTaskDefinition(WorkflowTask taskToSchedule) throws TerminateWorkflowException { //TODO this is a common pattern in code base can be moved to DAO
        TaskDef taskDefinition = Optional.ofNullable(taskToSchedule.getTaskDefinition())
                .orElseThrow(() -> {
                    String reason = String.format("Invalid task specified.  Cannot find task by name %s in the task definitions",
                            taskToSchedule.getName());
                    return new TerminateWorkflowException(reason);
                });
        return taskDefinition;
    }