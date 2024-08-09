    @Test
    public void getEvaluatedCaseValue() {
        WorkflowTask decisionTask = new WorkflowTask();
        decisionTask.setType(TaskType.DECISION.name());
        decisionTask.setName("Decision");
        decisionTask.setTaskReferenceName("decisionTask");
        decisionTask.setInputParameters(ip1);
        decisionTask.setDefaultCase(Arrays.asList(task1));
        decisionTask.setCaseValueParam("case");
        Map<String, List<WorkflowTask>> decisionCases = new HashMap<>();
        decisionCases.put("0", Arrays.asList(task2));
        decisionCases.put("1", Arrays.asList(task3));
        decisionTask.setDecisionCases(decisionCases);

        Workflow workflowInstance = new Workflow();
        workflowInstance.setWorkflowDefinition(new WorkflowDef());
        Map<String, Object> workflowInput = new HashMap<>();
        workflowInput.put("param1", "test1");
        workflowInput.put("param2", "test2");
        workflowInput.put("case", "0");
        workflowInstance.setInput(workflowInput);

        Map<String, Object> input = parametersUtils.getTaskInput(decisionTask.getInputParameters(),
                workflowInstance, null, null);

        assertEquals("0", decisionTaskMapper.getEvaluatedCaseValue(decisionTask, input));
    }