@Nonnull
	MultiTaskSlot createRootSlot(
			SlotRequestId slotRequestId,
			CompletableFuture<? extends SlotContext> slotContextFuture,
			SlotRequestId allocatedSlotRequestId) {
		final CompletableFuture<SlotContext> slotContextFutureAfterRootSlotResolution = new CompletableFuture<>();
		final MultiTaskSlot rootMultiTaskSlot = new MultiTaskSlot(
			slotRequestId,
			slotContextFutureAfterRootSlotResolution,
			allocatedSlotRequestId);

		LOG.debug("Create multi task slot [{}] in slot [{}].", slotRequestId, allocatedSlotRequestId);

		allTaskSlots.put(slotRequestId, rootMultiTaskSlot);
		unresolvedRootSlots.put(slotRequestId, rootMultiTaskSlot);

		FutureUtils.forward(
			slotContextFuture.thenApply(
				(SlotContext slotContext) -> {
					// add the root node to the set of resolved root nodes once the SlotContext future has
					// been completed and we know the slot's TaskManagerLocation
					tryMarkSlotAsResolved(slotRequestId, slotContext);
					return slotContext;
				}),
			slotContextFutureAfterRootSlotResolution);

		return rootMultiTaskSlot;
	}