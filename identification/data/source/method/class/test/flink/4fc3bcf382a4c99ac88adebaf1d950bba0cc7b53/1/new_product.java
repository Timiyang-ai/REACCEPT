@Nonnull
	MultiTaskSlot createRootSlot(
			SlotRequestId slotRequestId,
			CompletableFuture<? extends SlotContext> slotContextFuture,
			SlotRequestId allocatedSlotRequestId) {
		final MultiTaskSlot rootMultiTaskSlot = new MultiTaskSlot(
			slotRequestId,
			slotContextFuture,
			allocatedSlotRequestId);

		LOG.debug("Create multi task slot [{}] in slot [{}].", slotRequestId, allocatedSlotRequestId);

		allTaskSlots.put(slotRequestId, rootMultiTaskSlot);

		unresolvedRootSlots.put(slotRequestId, rootMultiTaskSlot);

		// add the root node to the set of resolved root nodes once the SlotContext future has
		// been completed and we know the slot's TaskManagerLocation
		slotContextFuture.whenComplete(
			(SlotContext slotContext, Throwable throwable) -> {
				if (slotContext != null) {
					final MultiTaskSlot resolvedRootNode = unresolvedRootSlots.remove(slotRequestId);

					if (resolvedRootNode != null) {
						final AllocationID allocationId = slotContext.getAllocationId();
						LOG.trace("Fulfill multi task slot [{}] with slot [{}].", slotRequestId, allocationId);

						final Map<AllocationID, MultiTaskSlot> innerMap = resolvedRootSlots.computeIfAbsent(
							slotContext.getTaskManagerLocation(),
							taskManagerLocation -> new HashMap<>(4));

						MultiTaskSlot previousValue = innerMap.put(allocationId, resolvedRootNode);
						Preconditions.checkState(previousValue == null);
					}
				} else {
					rootMultiTaskSlot.release(throwable);
				}
			});

		return rootMultiTaskSlot;
	}