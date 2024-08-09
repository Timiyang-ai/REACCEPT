public Collection<SlotKey> getChildrenKeys(Granularity destGranularity) {

        if (!getGranularity().isCoarser(destGranularity)) {
            throw new IllegalArgumentException("Current granularity must be coarser than the destination granularity");
        }

        List<SlotKey> result = new ArrayList<SlotKey>();
        for(SlotKey slotKey: this.getChildrenKeys()) {
            if (slotKey.getGranularity().equals(destGranularity)) {
                result.add(slotKey);
            }
        }

        return Collections.unmodifiableList(result);
    }