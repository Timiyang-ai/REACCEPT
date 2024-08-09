    @Test
    public void getMappedTasks() {

        //Given
        //Task Definition
        TaskDef taskDef = new TaskDef();
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("Id", "${workflow.input.Id}");
        List<Map<String, Object>> taskDefinitionInput = new LinkedList<>();
        taskDefinitionInput.add(inputMap);

        //Decision task instance
        WorkflowTask decisionTask = new WorkflowTask();
        decisionTask.setType(TaskType.DECISION.name());
        decisionTask.setName("Decision");
        decisionTask.setTaskReferenceName("decisionTask");
        decisionTask.setDefaultCase(Arrays.asList(task1));
        decisionTask.setCaseValueParam("case");
        decisionTask.getInputParameters().put("Id", "${workflow.input.Id}");
        decisionTask.setCaseExpression("if ($.Id == null) 'bad input'; else if ( ($.Id != null && $.Id % 2 == 0)) 'even'; else 'odd'; ");
        Map<String, List<WorkflowTask>> decisionCases = new HashMap<>();
        decisionCases.put("even", Arrays.asList(task2));
        decisionCases.put("odd", Arrays.asList(task3));
        decisionTask.setDecisionCases(decisionCases);
        //Workflow instance
        WorkflowDef workflowDef = new WorkflowDef();
        workflowDef.setSchemaVersion(2);

        Workflow workflowInstance = new Workflow();
        workflowInstance.setWorkflowDefinition(workflowDef);
        Map<String, Object> workflowInput = new HashMap<>();
        workflowInput.put("Id", "22");
        workflowInstance.setInput(workflowInput);

        Map<String, Object> body = new HashMap<>();
        body.put("input", taskDefinitionInput);
        taskDef.getInputTemplate().putAll(body);

        Map<String, Object> input = parametersUtils.getTaskInput(decisionTask.getInputParameters(),
                workflowInstance, null, null);


        Task theTask = new Task();
        theTask.setReferenceTaskName("Foo");
        theTask.setTaskId(IDGenerator.generate());

        when(deciderService.getTasksToBeScheduled(workflowInstance, task2, 0, null))
                .thenReturn(Arrays.asList(theTask));

        TaskMapperContext taskMapperContext = TaskMapperContext.newBuilder()
                .withWorkflowDefinition(workflowDef)
                .withWorkflowInstance(workflowInstance)
                .withTaskToSchedule(decisionTask)
                .withTaskInput(input)
                .withRetryCount(0)
                .withTaskId(IDGenerator.generate())
                .withDeciderService(deciderService)
                .build();

        //When
        List<Task> mappedTasks = decisionTaskMapper.getMappedTasks(taskMapperContext);

        //Then
        assertEquals(2, mappedTasks.size());
        assertEquals("decisionTask", mappedTasks.get(0).getReferenceTaskName());
        assertEquals("Foo", mappedTasks.get(1).getReferenceTaskName());

    }