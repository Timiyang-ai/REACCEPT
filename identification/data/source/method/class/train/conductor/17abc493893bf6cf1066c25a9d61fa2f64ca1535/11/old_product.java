@VisibleForTesting
    Pair<List<WorkflowTask>, Map<String, Map<String, Object>>> getDynamicForkTasksAndInput(WorkflowTask taskToSchedule, Workflow workflowInstance,
                                                                                           String dynamicForkTaskParam) throws TerminateWorkflow {

        Map<String, Object> input = parametersUtils.getTaskInput(taskToSchedule.getInputParameters(), workflowInstance, null, null);
        Object dynamicForkTasksJson = input.get(dynamicForkTaskParam);
        List<WorkflowTask> dynamicForkWorkflowTasks = objectMapper.convertValue(dynamicForkTasksJson, ListOfWorkflowTasks);
        Object dynamicForkTasksInput = input.get(taskToSchedule.getDynamicForkTasksInputParamName());
        if (!(dynamicForkTasksInput instanceof Map)) {
            throw new TerminateWorkflow("Input to the dynamically forked tasks is not a map -> expecting a map of K,V  but found " + dynamicForkTasksInput);
        }
        return new ImmutablePair<>(dynamicForkWorkflowTasks, (Map<String, Map<String, Object>>) dynamicForkTasksInput);
    }