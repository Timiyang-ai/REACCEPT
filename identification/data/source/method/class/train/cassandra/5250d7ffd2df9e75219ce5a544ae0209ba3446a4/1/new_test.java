@Test
    public void testUpdate_UpdateFunctionCallBack()
    {
        Object[] btree = new Object[1];
        CallsMonitor monitor = new CallsMonitor();

        btree = BTree.update(btree, CMP, Arrays.asList(1), monitor);
        assertArrayEquals(new Object[] {1}, btree);
        assertEquals(1, monitor.getNumberOfCalls(1));

        monitor.clear();
        btree = BTree.update(btree, CMP, Arrays.asList(2), monitor);
        assertArrayEquals(new Object[] {1, 2, null}, btree);
        assertEquals(1, monitor.getNumberOfCalls(2));

        // with existing value
        monitor.clear();
        btree = BTree.update(btree, CMP, Arrays.asList(1), monitor);
        assertArrayEquals(new Object[] {1, 2, null}, btree);
        assertEquals(1, monitor.getNumberOfCalls(1));

        // with two non-existing values
        monitor.clear();
        btree = BTree.update(btree, CMP, Arrays.asList(3, 4), monitor);
        assertArrayEquals(new Object[] {1, 2, 3, 4, null}, btree);
        assertEquals(1, monitor.getNumberOfCalls(3));
        assertEquals(1, monitor.getNumberOfCalls(4));

        // with one existing value and one non existing value
        monitor.clear();
        btree = BTree.update(btree, CMP, Arrays.asList(2, 5), monitor);
        assertArrayEquals(new Object[] {3, new Object[]{1, 2, null}, new Object[]{4, 5, null},  new int[]{2, 5}}, btree);
        assertEquals(1, monitor.getNumberOfCalls(2));
        assertEquals(1, monitor.getNumberOfCalls(5));
    }