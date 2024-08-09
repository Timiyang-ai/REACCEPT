    @Test
    public void getDynamicForkTasksAndInput() {
        //Given
        WorkflowTask dynamicForkJoinToSchedule = new WorkflowTask();
        dynamicForkJoinToSchedule.setType(TaskType.FORK_JOIN_DYNAMIC.name());
        dynamicForkJoinToSchedule.setTaskReferenceName("dynamicfanouttask");
        dynamicForkJoinToSchedule.setDynamicForkTasksParam("dynamicTasks");
        dynamicForkJoinToSchedule.setDynamicForkTasksInputParamName("dynamicTasksInput");
        dynamicForkJoinToSchedule.getInputParameters().put("dynamicTasks", "dt1.output.dynamicTasks");
        dynamicForkJoinToSchedule.getInputParameters().put("dynamicTasksInput", "dt1.output.dynamicTasksInput");

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

        Pair<List<WorkflowTask>, Map<String, Map<String, Object>>> dynamicTasks = forkJoinDynamicTaskMapper.getDynamicForkTasksAndInput(dynamicForkJoinToSchedule, new Workflow(), "dynamicTasks");

        //then
        assertNotNull(dynamicTasks.getLeft());
    }