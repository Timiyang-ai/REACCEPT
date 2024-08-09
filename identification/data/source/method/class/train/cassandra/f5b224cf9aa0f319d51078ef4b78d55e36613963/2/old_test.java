    @Test
    public void addAllTest()
    {
        RangeTombstoneList l1 = new RangeTombstoneList(cmp, 0);
        l1.add(rt(0, 4, 5));
        l1.add(rt(6, 10, 2));
        l1.add(rt(15, 17, 1));

        RangeTombstoneList l2 = new RangeTombstoneList(cmp, 0);
        l2.add(rt(3, 5, 7));
        l2.add(rt(7, 8, 3));
        l2.add(rt(8, 12, 1));
        l2.add(rt(14, 17, 4));

        l1.addAll(l2);

        Iterator<RangeTombstone> iter = l1.iterator();
        assertRT(rtie(0, 3, 5), iter.next());
        assertRT(rt(3, 5, 7), iter.next());
        assertRT(rtie(6, 7, 2), iter.next());
        assertRT(rt(7, 8, 3), iter.next());
        assertRT(rtei(8, 10, 2), iter.next());
        assertRT(rtei(10, 12, 1), iter.next());
        assertRT(rt(14, 17, 4), iter.next());

        assert !iter.hasNext();
    }