@Override
    public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) throws TerminateWorkflow {
        logger.debug("TaskMapperContext {} in ForkJoinDynamicTaskMapper", taskMapperContext);

        WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
        Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
        WorkflowDef workflowDef = taskMapperContext.getWorkflowDefinition();
        String taskId = taskMapperContext.getTaskId();
        int retryCount = taskMapperContext.getRetryCount();

        List<Task> mappedTasks = new LinkedList<>();
        //Get the list of dynamic tasks and the input for the tasks
        Pair<List<WorkflowTask>, Map<String, Map<String, Object>>> workflowTasksAndInputPair =
                Optional.ofNullable(taskToSchedule.getDynamicForkTasksParam())
                        .map(dynamicForkTaskParam -> getDynamicForkTasksAndInput(taskToSchedule, workflowInstance, dynamicForkTaskParam))
                        .orElseGet(() -> getDynamicForkJoinTasksAndInput(taskToSchedule, workflowInstance));

        List<WorkflowTask> dynForkTasks = workflowTasksAndInputPair.getLeft();
        Map<String, Map<String, Object>> tasksInput = workflowTasksAndInputPair.getRight();

        // Create Fork Task which needs to be followed by the dynamic tasks
        Task forkDynamicTask = createDynamicForkTask(taskToSchedule, workflowInstance, taskId, dynForkTasks);

        mappedTasks.add(forkDynamicTask);

        List<String> joinOnTaskRefs = new LinkedList<>();
        //Add each dynamic task to the mapped tasks and also get the last dynamic task in the list,
        // which indicates that the following task after that needs to be a join task
        for (WorkflowTask wft : dynForkTasks) {//TODO this is a cyclic dependency, break it out using function composition
            List<Task> forkedTasks = taskMapperContext.getDeciderService().getTasksToBeScheduled(workflowDef, workflowInstance, wft, retryCount);
            for (Task forkedTask : forkedTasks) {
                Map<String, Object> forkedTaskInput = tasksInput.get(forkedTask.getReferenceTaskName());
                forkedTask.getInputData().putAll(forkedTaskInput);
            }
            mappedTasks.addAll(forkedTasks);
            //Get the last of the dynamic tasks so that the join can be performed once this task is done
            Task last = forkedTasks.get(forkedTasks.size() - 1);
            joinOnTaskRefs.add(last.getReferenceTaskName());
        }

        //From the workflow definition get the next task and make sure that it is a JOIN task.
        //The dynamic fork tasks need to be followed by a join task
        WorkflowTask joinWorkflowTask = workflowDef.getNextTask(taskToSchedule.getTaskReferenceName());
        if (joinWorkflowTask == null || !joinWorkflowTask.getType().equals(WorkflowTask.Type.JOIN.name())) {
            throw new TerminateWorkflow("Dynamic join definition is not followed by a join task.  Check the blueprint");
        }

        // Create Join task
        HashMap<String, Object> joinInput = new HashMap<>();
        joinInput.put("joinOn", joinOnTaskRefs);
        Task joinTask = createJoinTask(workflowInstance, joinWorkflowTask, joinInput);
        mappedTasks.add(joinTask);

        return mappedTasks;
    }