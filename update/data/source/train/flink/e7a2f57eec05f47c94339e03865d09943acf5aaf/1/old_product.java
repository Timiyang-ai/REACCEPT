public void freeSlot(SlotID slotId, AllocationID allocationId) {
		checkInit();

		TaskManagerSlot slot = slots.get(slotId);

		if (null != slot) {
			if (slot.isAllocated()) {
				if (Objects.equals(allocationId, slot.getAllocationId())) {
					// free the slot
					slot.setAllocationId(null);
					fulfilledSlotRequests.remove(allocationId);

					if (slot.isFree()) {
						handleFreeSlot(slot);
					}

					TaskManagerRegistration taskManagerRegistration = taskManagerRegistrations.get(slot.getInstanceId());

					if (null != taskManagerRegistration) {
						if (anySlotUsed(taskManagerRegistration.getSlots())) {
							taskManagerRegistration.markUsed();
						} else {
							taskManagerRegistration.markIdle();
						}
					}

				} else {
					LOG.debug("Received request to free slot {} with expected allocation id {}, " +
						"but actual allocation id {} differs. Ignoring the request.", slotId, allocationId, slot.getAllocationId());
				}
			} else {
				LOG.debug("Slot {} has not been allocated.", allocationId);
			}
		} else {
			LOG.debug("Trying to free a slot {} which has not been registered. Ignoring this message.", slotId);
		}
	}