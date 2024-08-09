public static List<BulletinEntity> mergeBulletins(final Map<NodeIdentifier, List<BulletinEntity>> bulletins) {
        final List<BulletinEntity> bulletinDtos = new ArrayList<>();

        for (final Map.Entry<NodeIdentifier, List<BulletinEntity>> entry : bulletins.entrySet()) {
            final NodeIdentifier nodeId = entry.getKey();
            final List<BulletinEntity> nodeBulletins = entry.getValue();
            final String nodeAddress = nodeId.getApiAddress() + ":" + nodeId.getApiPort();

            for (final BulletinEntity bulletin : nodeBulletins) {
                if (bulletin.getNodeAddress() == null) {
                    bulletin.setNodeAddress(nodeAddress);
                }

                bulletinDtos.add(bulletin);
            }
        }

        Collections.sort(bulletinDtos, (BulletinEntity o1, BulletinEntity o2) -> {
            final int timeComparison = o1.getTimestamp().compareTo(o2.getTimestamp());
            if (timeComparison != 0) {
                return timeComparison;
            }

            return o1.getNodeAddress().compareTo(o2.getNodeAddress());
        });

        return bulletinDtos;
    }