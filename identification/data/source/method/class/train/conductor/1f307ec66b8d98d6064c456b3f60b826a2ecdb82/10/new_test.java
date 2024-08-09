    @Test
    public void getMappedTasks() {

        Mockito.doReturn(Arrays.asList(task1)).when(deciderService).getTasksToBeScheduled(workflow, workflowTask1, 0);

        List<Task> mappedTasks = new DoWhileTaskMapper(metadataDAO).getMappedTasks(taskMapperContext);

        assertNotNull(mappedTasks);
        assertEquals(mappedTasks.size(), 2);
        assertEquals("task1__1", mappedTasks.get(1).getReferenceTaskName());
        assertEquals(1, mappedTasks.get(1).getIteration());
        assertEquals(SystemTaskType.DO_WHILE.name(), mappedTasks.get(0).getTaskType());
    }