@Override
     public List<Task> getMappedTasks(TaskMapperContext taskMapperContext) throws TerminateWorkflowException {

         logger.debug("TaskMapperContext {} in HTTPTaskMapper", taskMapperContext);

         WorkflowTask taskToSchedule = taskMapperContext.getTaskToSchedule();
         taskToSchedule.getInputParameters().put("asyncComplete", taskToSchedule.isAsyncComplete());
         Workflow workflowInstance = taskMapperContext.getWorkflowInstance();
         String taskId = taskMapperContext.getTaskId();
         int retryCount = taskMapperContext.getRetryCount();

         TaskDef taskDefinition = Optional.ofNullable(taskMapperContext.getTaskDefinition())
                 .orElseGet(() -> Optional.ofNullable(metadataDAO.getTaskDef(taskToSchedule.getName()))
                         .orElseThrow(() -> {
                             String reason = String.format("Invalid task specified. Cannot find task by name %s in the task definitions", taskToSchedule.getName());
                             return new TerminateWorkflowException(reason);
                         }));

         Map<String, Object> input = parametersUtils.getTaskInputV2(taskToSchedule.getInputParameters(), workflowInstance, taskId, taskDefinition);
         Boolean asynComplete = (Boolean)input.get("asyncComplete");

         Task httpTask = new Task();
         httpTask.setTaskType(taskToSchedule.getType());
         httpTask.setTaskDefName(taskToSchedule.getName());
         httpTask.setReferenceTaskName(taskToSchedule.getTaskReferenceName());
         httpTask.setWorkflowInstanceId(workflowInstance.getWorkflowId());
         httpTask.setWorkflowType(workflowInstance.getWorkflowName());
         httpTask.setCorrelationId(workflowInstance.getCorrelationId());
         httpTask.setScheduledTime(System.currentTimeMillis());
         httpTask.setTaskId(taskId);
         httpTask.setInputData(input);
         httpTask.getInputData().put("asyncComplete", asynComplete);
         httpTask.setStatus(Task.Status.SCHEDULED);
         httpTask.setRetryCount(retryCount);
         httpTask.setCallbackAfterSeconds(taskToSchedule.getStartDelay());
         httpTask.setWorkflowTask(taskToSchedule);
         httpTask.setWorkflowPriority(workflowInstance.getPriority());
         httpTask.setRateLimitPerFrequency(taskDefinition.getRateLimitPerFrequency());
         httpTask.setRateLimitFrequencyInSeconds(taskDefinition.getRateLimitFrequencyInSeconds());
         httpTask.setIsolationGroupId(taskDefinition.getIsolationGroupId());
         httpTask.setDomain(taskDefinition.getExecutionNameSpace());
         return Collections.singletonList(httpTask);
     }