    @Test
    public void getMappedTasks() {

        WorkflowDef def = new WorkflowDef();
        def.setName("DYNAMIC_FORK_JOIN_WF");
        def.setDescription(def.getName());
        def.setVersion(1);
        def.setInputParameters(Arrays.asList("param1", "param2"));

        Workflow  workflowInstance = new Workflow();
        workflowInstance.setWorkflowDefinition(def);

        WorkflowTask dynamicForkJoinToSchedule = new WorkflowTask();
        dynamicForkJoinToSchedule.setType(TaskType.FORK_JOIN_DYNAMIC.name());
        dynamicForkJoinToSchedule.setTaskReferenceName("dynamicfanouttask");
        dynamicForkJoinToSchedule.setDynamicForkTasksParam("dynamicTasks");
        dynamicForkJoinToSchedule.setDynamicForkTasksInputParamName("dynamicTasksInput");
        dynamicForkJoinToSchedule.getInputParameters().put("dynamicTasks", "dt1.output.dynamicTasks");
        dynamicForkJoinToSchedule.getInputParameters().put("dynamicTasksInput", "dt1.output.dynamicTasksInput");


        WorkflowTask join = new WorkflowTask();
        join.setType(TaskType.JOIN.name());
        join.setTaskReferenceName("dynamictask_join");

        def.getTasks().add(dynamicForkJoinToSchedule);
        def.getTasks().add(join);

        Map<String, Object> input1 = new HashMap<>();
        input1.put("k1", "v1");
        WorkflowTask wt2 = new WorkflowTask();
        wt2.setName("junit_task_2");
        wt2.setTaskReferenceName("xdt1");

        Map<String, Object> input2 = new HashMap<>();
        input2.put("k2", "v2");

        WorkflowTask wt3 = new WorkflowTask();
        wt3.setName("junit_task_3");
        wt3.setTaskReferenceName("xdt2");

        HashMap<String, Object> dynamicTasksInput = new HashMap<>();
        dynamicTasksInput.put("xdt1", input1);
        dynamicTasksInput.put("xdt2", input2);
        dynamicTasksInput.put("dynamicTasks", Arrays.asList(wt2, wt3));
        dynamicTasksInput.put("dynamicTasksInput", dynamicTasksInput);

        //when
        when(parametersUtils.getTaskInput(anyMap(), any(Workflow.class), any(TaskDef.class), anyString()))
                .thenReturn(dynamicTasksInput);
        when(objectMapper.convertValue(anyMap(),any(TypeReference.class))).thenReturn(Arrays.asList(wt2, wt3));


        Task simpleTask1 = new Task();
        simpleTask1.setReferenceTaskName("xdt1");

        Task simpleTask2 = new Task();
        simpleTask2.setReferenceTaskName("xdt2");

        when(deciderService.getTasksToBeScheduled(workflowInstance, wt2, 0 )).thenReturn(Arrays.asList(simpleTask1));
        when(deciderService.getTasksToBeScheduled(workflowInstance, wt3, 0 )).thenReturn(Arrays.asList(simpleTask2));

        String taskId = IDGenerator.generate();
        TaskMapperContext taskMapperContext = TaskMapperContext.newBuilder()
                .withWorkflowDefinition(def)
                .withWorkflowInstance(workflowInstance)
                .withTaskToSchedule(dynamicForkJoinToSchedule)
                .withRetryCount(0)
                .withTaskId(taskId)
                .withDeciderService(deciderService)
                .build();

        //then
        List<Task> mappedTasks = forkJoinDynamicTaskMapper.getMappedTasks(taskMapperContext);

        assertEquals(4, mappedTasks.size());

        assertEquals(SystemTaskType.FORK.name(),mappedTasks.get(0).getTaskType());
        assertEquals(SystemTaskType.JOIN.name(), mappedTasks.get(3).getTaskType());
        List<String> joinTaskNames = (List<String>)mappedTasks.get(3).getInputData().get("joinOn");
        assertEquals("xdt1, xdt2", joinTaskNames.stream().collect(Collectors.joining(", ")));

    }