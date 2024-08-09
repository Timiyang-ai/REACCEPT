public Collection<SlotKey> getChildrenKeys(Granularity destGranularity) {

        if (!getGranularity().isCoarser(destGranularity)) {
            throw new IllegalArgumentException(String.format("Current granularity [%s] must be coarser than the destination granularity [%s]", getGranularity(), destGranularity));
        }

        List<SlotKey> result = new ArrayList<SlotKey>();
        for(SlotKey slotKey: this.getChildrenKeys()) {
            if (slotKey.getGranularity().equals(destGranularity)) {
                result.add(slotKey);
            }
        }

        return Collections.unmodifiableList(result);
    }