@VisibleForTesting
    Pair<List<WorkflowTask>, Map<String, Map<String, Object>>> getDynamicForkJoinTasksAndInput(WorkflowTask taskToSchedule, Workflow workflowInstance) throws TerminateWorkflowException {
        String dynamicForkJoinTaskParam = taskToSchedule.getDynamicForkJoinTasksParam();
        Map<String, Object> input = parametersUtils.getTaskInput(taskToSchedule.getInputParameters(), workflowInstance, null, null);
        Object paramValue = input.get(dynamicForkJoinTaskParam);
        DynamicForkJoinTaskList dynamicForkJoinTaskList = objectMapper.convertValue(paramValue, DynamicForkJoinTaskList.class);

        if (dynamicForkJoinTaskList == null) {
            String reason = String.format("Dynamic tasks could not be created. The value of %s from task's input %s has no dynamic tasks to be scheduled", dynamicForkJoinTaskParam, input);
            logger.error(reason);
            throw new TerminateWorkflowException(reason);
        }

        Map<String, Map<String, Object>> dynamicForkJoinTasksInput = new HashMap<>();

        List<WorkflowTask> dynamicForkJoinWorkflowTasks = dynamicForkJoinTaskList.getDynamicTasks().stream()
                .peek(dynamicForkJoinTask -> dynamicForkJoinTasksInput.put(dynamicForkJoinTask.getReferenceName(), dynamicForkJoinTask.getInput())) //TODO create a custom pair collector
                .map(dynamicForkJoinTask -> {
                    WorkflowTask dynamicForkJoinWorkflowTask = new WorkflowTask();
                    dynamicForkJoinWorkflowTask.setTaskReferenceName(dynamicForkJoinTask.getReferenceName());
                    dynamicForkJoinWorkflowTask.setName(dynamicForkJoinTask.getTaskName());
                    dynamicForkJoinWorkflowTask.setType(dynamicForkJoinTask.getType());
                    if (MetadataMapperService.shouldPopulateDefinition(dynamicForkJoinWorkflowTask)) {
                        dynamicForkJoinWorkflowTask.setTaskDefinition(
                                metadataDAO.getTaskDef(dynamicForkJoinTask.getTaskName()));
                    }
                    return dynamicForkJoinWorkflowTask;
                })
                .collect(Collectors.toCollection(LinkedList::new));

        return new ImmutablePair<>(dynamicForkJoinWorkflowTasks, dynamicForkJoinTasksInput);
    }