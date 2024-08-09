@Test(description = "POST /task/{id}/action/cancel", dependsOnMethods = { "testGetTask" })
   public void testCancelTask() {
      vApp = instantiateVApp();
      
      Task task = Iterables.getFirst(vApp.getTasks(), null);
      assertNotNull(task, "instantiateVApp should contain one long-running task");
      assertTaskStatusEventually(task, Task.Status.RUNNING, ImmutableSet.of(Task.Status.ERROR, Task.Status.ABORTED));

      // Call the method being tested
      taskApi.cancel(taskURI);
      assertTaskStatusEventually(task, Task.Status.CANCELED, ImmutableSet.of(Task.Status.ERROR, Task.Status.ABORTED, Task.Status.SUCCESS));
   }