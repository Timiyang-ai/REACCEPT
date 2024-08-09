    @Test
    public void mergeBulletins() throws Exception {
        final BulletinEntity bulletinEntity1 = createBulletin("This is bulletin 1");
        final BulletinEntity bulletinEntity2 = createBulletin("This is bulletin 2");

        final BulletinEntity unauthorizedBulletin = new BulletinEntity();
        unauthorizedBulletin.setId(bulletinId++);
        unauthorizedBulletin.setTimestamp(new Date());
        unauthorizedBulletin.setCanRead(false);

        final BulletinEntity copyOfBulletin1 = createBulletin("This is bulletin 1");

        final NodeIdentifier node1 = new NodeIdentifier("node-1", "host-1", 8080, "host-1", 19998, null, null, null, false);
        final NodeIdentifier node2 = new NodeIdentifier("node-2", "host-2", 8081, "host-2", 19999, null, null, null, false);

        final Map<NodeIdentifier, List<BulletinEntity>> nodeMap = new HashMap<>();
        nodeMap.put(node1, new ArrayList<>());
        nodeMap.put(node2, new ArrayList<>());

        nodeMap.get(node1).add(bulletinEntity1);
        nodeMap.get(node1).add(bulletinEntity2);
        nodeMap.get(node1).add(unauthorizedBulletin);

        nodeMap.get(node2).add(copyOfBulletin1);

        final List<BulletinEntity> bulletinEntities = BulletinMerger.mergeBulletins(nodeMap, nodeMap.size());
        assertEquals(bulletinEntities.size(), 3);
        assertTrue(bulletinEntities.contains(copyOfBulletin1));
        assertEquals(copyOfBulletin1.getNodeAddress(), ALL_NODES_MESSAGE);
        assertTrue(bulletinEntities.contains(bulletinEntity2));
        assertTrue(bulletinEntities.contains(unauthorizedBulletin));
    }