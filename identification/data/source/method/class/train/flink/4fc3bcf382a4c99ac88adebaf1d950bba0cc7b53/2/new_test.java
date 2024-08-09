	private SlotSharingManager.MultiTaskSlot createRootSlot(TaskManagerLocation firstTaskExecutorLocation, SlotSharingManager slotSharingManager) {
		return slotSharingManager.createRootSlot(
			new SlotRequestId(),
			CompletableFuture.completedFuture(
				new SimpleSlotContext(
					new AllocationID(),
					firstTaskExecutorLocation,
					0,
					new SimpleAckingTaskManagerGateway())),
			new SlotRequestId());
	}