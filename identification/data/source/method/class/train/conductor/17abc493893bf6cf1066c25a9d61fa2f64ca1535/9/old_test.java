    @Test
    public void getDynamicForkJoinTasksAndInput() {
        //Given
        WorkflowTask dynamicForkJoinToSchedule = new WorkflowTask();
        dynamicForkJoinToSchedule.setType(TaskType.FORK_JOIN_DYNAMIC.name());
        dynamicForkJoinToSchedule.setTaskReferenceName("dynamicfanouttask");
        dynamicForkJoinToSchedule.setDynamicForkJoinTasksParam("dynamicTasks");
        dynamicForkJoinToSchedule.getInputParameters().put("dynamicTasks", "dt1.output.dynamicTasks");
        dynamicForkJoinToSchedule.getInputParameters().put("dynamicTasksInput", "dt1.output.dynamicTasksInput");

        DynamicForkJoinTaskList dtasks = new DynamicForkJoinTaskList();

        Map<String, Object> input = new HashMap<>();
        input.put("k1", "v1");
        dtasks.add("junit_task_2", null, "xdt1", input);

        HashMap<String, Object> input2 = new HashMap<>();
        input2.put("k2", "v2");
        dtasks.add("junit_task_3", null, "xdt2", input2);

        Map<String, Object> dynamicTasksInput = new HashMap<>();
        dynamicTasksInput.put("dynamicTasks", dtasks);

        //when
        when(parametersUtils.getTaskInput(anyMap(), any(Workflow.class), any(TaskDef.class), anyString()))
                .thenReturn(dynamicTasksInput);

        when(objectMapper.convertValue(anyObject(),any(Class.class))).thenReturn(dtasks);

        Pair<List<WorkflowTask>, Map<String, Map<String, Object>>> dynamicForkJoinTasksAndInput =
                forkJoinDynamicTaskMapper.getDynamicForkJoinTasksAndInput(dynamicForkJoinToSchedule, new Workflow());
        //then
        assertNotNull(dynamicForkJoinTasksAndInput.getLeft());
        assertEquals(2,dynamicForkJoinTasksAndInput.getLeft().size());
        assertEquals(2, dynamicForkJoinTasksAndInput.getRight().size());

    }