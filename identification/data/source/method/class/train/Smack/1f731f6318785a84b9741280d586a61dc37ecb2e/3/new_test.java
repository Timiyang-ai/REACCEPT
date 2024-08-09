    @Test
    public void generateOmemoPreKeys() {
        TreeMap<Integer, T_PreKey> keys = store.generateOmemoPreKeys(31, 49);
        assertNotNull("Generated data structure must not be null.", keys);

        byte[] lastKey = null;

        for (int i = 31; i <= 79; i++) {
            assertEquals("Key ids must be ascending order, starting at 31.", Integer.valueOf(i), keys.firstKey());
            assertNotNull("Every id must match to a key.", keys.get(keys.firstKey()));
            byte[] bytes = store.keyUtil().preKeyToBytes(keys.get(keys.firstKey()));
            assertNotNull("Serialized preKey must not be null.", bytes);
            assertNotSame("Serialized preKey must not be of length 0.", 0, bytes.length);

            if (lastKey != null) {
                assertFalse("PreKeys MUST NOT be equal.", Arrays.equals(lastKey, bytes));
            }
            lastKey = bytes;

            keys.remove(keys.firstKey());

        }

        assertEquals("After deleting 49 keys, there must be no keys left.", 0, keys.size());
    }