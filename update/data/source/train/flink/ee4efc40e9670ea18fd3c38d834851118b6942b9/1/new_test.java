@Test
	public void testFreeSlot() throws Exception {
		final ResourceManagerId resourceManagerId = ResourceManagerId.generate();
		final ResourceID resourceID = ResourceID.generate();
		final JobID jobId = new JobID();
		final SlotID slotId = new SlotID(resourceID, 0);
		final AllocationID allocationId = new AllocationID();
		final ResourceProfile resourceProfile = new ResourceProfile(42.0, 1337);

		ResourceActions resourceManagerActions = mock(ResourceActions.class);

		// accept an incoming slot request
		final TaskExecutorGateway taskExecutorGateway = mock(TaskExecutorGateway.class);

		final TaskExecutorConnection taskExecutorConnection = new TaskExecutorConnection(resourceID, taskExecutorGateway);

		final SlotStatus slotStatus = new SlotStatus(slotId, resourceProfile, jobId, allocationId);
		final SlotReport slotReport = new SlotReport(slotStatus);

		try (SlotManagerImpl slotManager = createSlotManager(resourceManagerId, resourceManagerActions)) {

			slotManager.registerTaskManager(
				taskExecutorConnection,
				slotReport);

			TaskManagerSlot slot = slotManager.getSlot(slotId);

			assertEquals("The slot has not been allocated to the expected allocation id.", allocationId, slot.getAllocationId());

			// this should be ignored since the allocation id does not match
			slotManager.freeSlot(slotId, new AllocationID());

			assertTrue(slot.getState() == TaskManagerSlot.State.ALLOCATED);
			assertEquals("The slot has not been allocated to the expected allocation id.", allocationId, slot.getAllocationId());

			slotManager.freeSlot(slotId, allocationId);

			assertTrue(slot.getState() == TaskManagerSlot.State.FREE);
			assertNull(slot.getAllocationId());
		}
	}