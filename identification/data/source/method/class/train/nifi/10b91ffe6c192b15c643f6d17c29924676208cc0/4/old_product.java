public static List<BulletinDTO> mergeBulletins(final Map<NodeIdentifier, List<BulletinDTO>> bulletins) {
        final List<BulletinDTO> bulletinDtos = new ArrayList<>();

        for (final Map.Entry<NodeIdentifier, List<BulletinDTO>> entry : bulletins.entrySet()) {
            final NodeIdentifier nodeId = entry.getKey();
            final List<BulletinDTO> nodeBulletins = entry.getValue();
            final String nodeAddress = nodeId.getApiAddress() + ":" + nodeId.getApiPort();

            for (final BulletinDTO bulletin : nodeBulletins) {
                bulletin.setNodeAddress(nodeAddress);
                bulletinDtos.add(bulletin);
            }
        }

        Collections.sort(bulletinDtos, (BulletinDTO o1, BulletinDTO o2) -> {
            final int timeComparison = o1.getTimestamp().compareTo(o2.getTimestamp());
            if (timeComparison != 0) {
                return timeComparison;
            }

            return o1.getNodeAddress().compareTo(o2.getNodeAddress());
        });

        return bulletinDtos;
    }