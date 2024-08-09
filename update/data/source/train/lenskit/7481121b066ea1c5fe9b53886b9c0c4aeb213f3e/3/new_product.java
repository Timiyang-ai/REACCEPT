@Override
    public ItemItemBuildContext get() {
        logger.info("constructing build context");
        logger.debug("using normalizer {}", normalizer);

        logger.debug("Building item data");
        Long2ObjectMap<LongList> userItems = new Long2ObjectOpenHashMap<LongList>(1000);
        Long2ObjectMap<SparseVector> itemVectors = new Long2ObjectOpenHashMap<SparseVector>(1000);
        Cursor<ItemEventCollection<Event>> itemCursor = itemEventDAO.streamEventsByItem();
        try {
            for (ItemEventCollection<Event> item: itemCursor) {
                if (logger.isTraceEnabled()) {
                    logger.trace("processing {} ratings for item {}", item.size(), item);
                }
                List<Rating> ratings = FluentIterable.from(item)
                                                     .filter(Rating.class)
                                                     .toList();
                MutableSparseVector vector = Ratings.itemRatingVector(ratings);
                normalizer.normalize(item.getItemId(), vector, vector);
                for (VectorEntry e: vector) {
                    long user = e.getKey();
                    LongList uis = userItems.get(user);
                    if (uis == null) {
                        // lists are nice and fast, we only see each item once
                        uis = new LongArrayList();
                        userItems.put(user, uis);
                    }
                    uis.add(item.getItemId());
                }
                itemVectors.put(item.getItemId(), vector.freeze());
            }
        } finally {
            itemCursor.close();
        }

        Long2ObjectMap<LongSortedSet> userItemSets = new Long2ObjectOpenHashMap<LongSortedSet>();
        for (Long2ObjectMap.Entry<LongList> entry: userItems.long2ObjectEntrySet()) {
            userItemSets.put(entry.getLongKey(), LongUtils.packedSet(entry.getValue()));
        }

        LongKeyDomain items = LongKeyDomain.fromCollection(itemVectors.keySet(), true);
        SparseVector[] itemData = new SparseVector[items.domainSize()];
        for (int i = 0; i < itemData.length; i++) {
            long itemId = items.getKey(i);
            itemData[i] = itemVectors.get(itemId);
        }

        logger.debug("item data completed");
        return new ItemItemBuildContext(items, itemData, userItemSets);
    }