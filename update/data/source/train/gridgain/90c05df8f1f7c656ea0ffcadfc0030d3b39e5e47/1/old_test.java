@Test
    public void testCheckConsistency() {
        UUID node1 = UUID.randomUUID();
        UUID node2 = UUID.randomUUID();
        UUID node3 = UUID.randomUUID();
        UUID node4 = UUID.randomUUID();

        Map<UUID, GridCacheVersion> oldKey = new HashMap<>();
        oldKey.put(node1, version(1));
        oldKey.put(node2, version(3));
        oldKey.put(node3, version(3));
        oldKey.put(node4, version(2));

        {
            Map<UUID, GridCacheVersion> actualKey = new HashMap<>(); // All keys was removed

            assertTrue(ConsistencyCheckUtils.checkConsistency(oldKey, actualKey));
        }

        {
            Map<UUID, GridCacheVersion> actualKey = new HashMap<>();
            actualKey.put(node1, version(1));
            actualKey.put(node2, version(3));
            actualKey.put(node3, version(4)); // Max version increase
            actualKey.put(node4, version(2));

            assertTrue(ConsistencyCheckUtils.checkConsistency(oldKey, actualKey));
        }

        {
            Map<UUID, GridCacheVersion> actualKey = new HashMap<>();
            actualKey.put(node1, version(1));
            actualKey.put(node2, version(3)); // Max of node 3 was removed
            actualKey.put(node4, version(2));

            assertTrue(ConsistencyCheckUtils.checkConsistency(oldKey, actualKey));
        }

        {
            Map<UUID, GridCacheVersion> actualKey = new HashMap<>();
            actualKey.put(node1, version(3)); // Min value like max
            actualKey.put(node2, version(3));
            actualKey.put(node3, version(3));
            actualKey.put(node4, version(3));

            assertTrue(ConsistencyCheckUtils.checkConsistency(oldKey, actualKey));
        }

        {
            Map<UUID, GridCacheVersion> actualKey = new HashMap<>();
            actualKey.put(node1, version(4)); // Min value greater then max
            actualKey.put(node2, version(3));
            actualKey.put(node3, version(3));
            actualKey.put(node4, version(4));

            assertTrue(ConsistencyCheckUtils.checkConsistency(oldKey, actualKey));
        }

        {
            Map<UUID, GridCacheVersion> actualKey = new HashMap<>();
            actualKey.put(node1, version(1)); // Nothing changed.
            actualKey.put(node2, version(3));
            actualKey.put(node3, version(3));
            actualKey.put(node4, version(2));

            assertFalse(ConsistencyCheckUtils.checkConsistency(oldKey, actualKey));
        }

        {
            Map<UUID, GridCacheVersion> actualKey = new HashMap<>();
            actualKey.put(node1, version(2)); // Not all min values were incremented.
            actualKey.put(node2, version(3));
            actualKey.put(node3, version(3));
            actualKey.put(node4, version(3));

            assertFalse(ConsistencyCheckUtils.checkConsistency(oldKey, actualKey));
        }

        {
            Map<UUID, GridCacheVersion> actualKey = new HashMap<>();
            actualKey.put(node2, version(3)); // Remove of one value is not enough
            actualKey.put(node3, version(3));
            actualKey.put(node4, version(3));

            assertFalse(ConsistencyCheckUtils.checkConsistency(oldKey, actualKey));
        }
    }