public static List<BulletinEntity> mergeBulletins(final Map<NodeIdentifier, List<BulletinEntity>> bulletins) {
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

        Collections.sort(bulletinEntities, (BulletinEntity o1, BulletinEntity o2) -> {
            final int timeComparison = o1.getTimestamp().compareTo(o2.getTimestamp());
            if (timeComparison != 0) {
                return timeComparison;
            }

            return o1.getNodeAddress().compareTo(o2.getNodeAddress());
        });

        return bulletinEntities;
    }