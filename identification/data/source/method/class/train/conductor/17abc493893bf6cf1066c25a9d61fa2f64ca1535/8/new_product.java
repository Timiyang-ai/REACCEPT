@VisibleForTesting
    TaskDef getDynamicTaskDefinition(WorkflowTask taskToSchedule) throws TerminateWorkflowException { //TODO this is a common pattern in code base can be moved to DAO
        return Optional.ofNullable(metadataDAO.getTaskDef(taskToSchedule.getName()))
                .orElseThrow(() -> {
                    String reason = String.format("Invalid task specified.  Cannot find task by name %s in the task definitions",
                            taskToSchedule.getName());
                    return new TerminateWorkflowException(reason);
                });
    }