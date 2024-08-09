public static List<BulletinEntity> mergeBulletins(final Map<NodeIdentifier, List<BulletinEntity>> bulletins, final int totalNodes) {
        final List<BulletinEntity> bulletinEntities = new ArrayList<>();

        for (final Map.Entry<NodeIdentifier, List<BulletinEntity>> entry : bulletins.entrySet()) {
            final NodeIdentifier nodeId = entry.getKey();
            final List<BulletinEntity> nodeBulletins = entry.getValue();
            final String nodeAddress = nodeId.getApiAddress() + ":" + nodeId.getApiPort();

            for (final BulletinEntity bulletinEntity : nodeBulletins) {
                if (bulletinEntity.getNodeAddress() == null) {
                    bulletinEntity.setNodeAddress(nodeAddress);
                }

                if (bulletinEntity.getCanRead() && bulletinEntity.getBulletin() != null && bulletinEntity.getBulletin().getNodeAddress() == null) {
                    bulletinEntity.getBulletin().setNodeAddress(nodeAddress);
                }
                bulletinEntities.add(bulletinEntity);
            }
        }

        final List<BulletinEntity> entities = Lists.newArrayList();

        // group by message when permissions allow
        final Map<String,List<BulletinEntity>> groupingEntities = bulletinEntities.stream()
                .filter(bulletinEntity -> bulletinEntity.getCanRead())
                .collect(Collectors.groupingBy(b -> b.getBulletin().getMessage()));

        // add one from each grouped bulletin when all nodes report the same message
        groupingEntities.forEach((message, groupedBulletinEntities) -> {
            if (groupedBulletinEntities.size() == totalNodes) {
                // get the most current bulletin
                final BulletinEntity selectedBulletinEntity = groupedBulletinEntities.stream()
                        .max(Comparator.comparingLong(bulletinEntity -> {
                            if (bulletinEntity.getTimestamp() == null) {
                                return 0;
                            } else {
                                return bulletinEntity.getTimestamp().getTime();
                            }
                        })).orElse(null);

                // should never be null, but just in case
                if (selectedBulletinEntity != null) {
                    selectedBulletinEntity.setNodeAddress(ALL_NODES_MESSAGE);
                    selectedBulletinEntity.getBulletin().setNodeAddress(ALL_NODES_MESSAGE);
                    entities.add(selectedBulletinEntity);
                }
            } else {
                // since all nodes didn't report the exact same bulletin, keep them all
                entities.addAll(groupedBulletinEntities);
            }
        });

        // ensure we also get the remainder of the bulletin entities
        bulletinEntities.stream()
                .filter(bulletinEntity -> !bulletinEntity.getCanRead())
                .forEach(entities::add);

        // ensure the bulletins are sorted by time
        Collections.sort(entities, (BulletinEntity o1, BulletinEntity o2) -> {
            final int timeComparison = o1.getTimestamp().compareTo(o2.getTimestamp());
            if (timeComparison != 0) {
                return timeComparison;
            }

            return o1.getNodeAddress().compareTo(o2.getNodeAddress());
        });

        return entities;
    }