    @Test
    public void getDynamicTaskName() {
        Map<String, Object> taskInput = new HashMap<>();
        taskInput.put("dynamicTaskName", "DynoTask");

        String dynamicTaskName = dynamicTaskMapper.getDynamicTaskName(taskInput, "dynamicTaskName");

        assertEquals("DynoTask", dynamicTaskName);
    }