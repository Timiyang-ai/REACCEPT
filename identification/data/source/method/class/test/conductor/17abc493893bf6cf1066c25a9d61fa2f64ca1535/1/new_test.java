    @Test
    public void getDynamicTaskDefinition() {
        //Given
        WorkflowTask workflowTask = new WorkflowTask();
        workflowTask.setName("Foo");
        TaskDef taskDef = new TaskDef();
        taskDef.setName("Foo");
        workflowTask.setTaskDefinition(taskDef);

        when(metadataDAO.getTaskDef(any())).thenReturn(new TaskDef());

        //when
        TaskDef dynamicTaskDefinition = dynamicTaskMapper.getDynamicTaskDefinition(workflowTask);

        assertEquals(dynamicTaskDefinition, taskDef);
    }