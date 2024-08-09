    @Test
    public void test_getChildrenKeys() {
        // the relationship between slots plays out here.
        int shard = 0;
        int slot = 0;

        // full res slots have no children.
        int expect = 0;
        assertEquals(expect, SlotKey.of(Granularity.FULL, slot, shard).getChildrenKeys().size());

        // min5 slots contain only their full res counterpart.
        expect = expect * 1 + 1;
        assertEquals(expect, SlotKey.of(Granularity.MIN_5, slot, shard).getChildrenKeys().size());

        // min20 slots contain their 4 min5 children and full res grandchildren.
        expect = expect * 4 + 4;
        assertEquals(expect, SlotKey.of(Granularity.MIN_20, slot, shard).getChildrenKeys().size());

        // and so on. the relationship between min60 and min20 is a factor of 3.
        expect = expect * 3 + 3;
        assertEquals(expect, SlotKey.of(Granularity.MIN_60, slot, shard).getChildrenKeys().size());

        // min240 and min60 is a factor of 4.
        expect = expect * 4 + 4;
        assertEquals(expect, SlotKey.of(Granularity.MIN_240, slot, shard).getChildrenKeys().size());

        // min1440 and min240 is a factor of 6.
        expect = expect * 6 + 6;
        assertEquals(expect, SlotKey.of(Granularity.MIN_1440, slot, shard).getChildrenKeys().size());
    }