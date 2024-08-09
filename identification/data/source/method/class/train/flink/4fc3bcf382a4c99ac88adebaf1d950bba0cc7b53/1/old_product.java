MultiTaskSlot createRootSlot(
			SlotRequestId slotRequestId,
			CompletableFuture<? extends SlotContext> slotContextFuture,
			SlotRequestId allocatedSlotRequestId) {
		final MultiTaskSlot rootMultiTaskSlot = new MultiTaskSlot(
			slotRequestId,
			slotContextFuture,
			allocatedSlotRequestId);

		allTaskSlots.put(slotRequestId, rootMultiTaskSlot);

		synchronized (lock) {
			unresolvedRootSlots.put(slotRequestId, rootMultiTaskSlot);
		}

		// add the root node to the set of resolved root nodes once the SlotContext future has
		// been completed and we know the slot's TaskManagerLocation
		slotContextFuture.whenComplete(
			(SlotContext slotContext, Throwable throwable) -> {
				if (slotContext != null) {
					synchronized (lock) {
						final MultiTaskSlot resolvedRootNode = unresolvedRootSlots.remove(slotRequestId);

						if (resolvedRootNode != null) {
							final Set<MultiTaskSlot> innerCollection = resolvedRootSlots.computeIfAbsent(
								slotContext.getTaskManagerLocation(),
								taskManagerLocation -> new HashSet<>(4));

							innerCollection.add(resolvedRootNode);
						}
					}
				} else {
					rootMultiTaskSlot.release(throwable);
				}
			});

		return rootMultiTaskSlot;
	}