    @Test
    public void searchTest()
    {
        RangeTombstoneList l = new RangeTombstoneList(cmp, 0);
        l.add(rt(0, 4, 5));
        l.add(rt(4, 6, 2));
        l.add(rt(9, 12, 1));
        l.add(rt(14, 15, 3));
        l.add(rt(15, 17, 6));

        assertEquals(null, l.searchDeletionTime(clustering(-1)));

        assertEquals(5, l.searchDeletionTime(clustering(0)).markedForDeleteAt());
        assertEquals(5, l.searchDeletionTime(clustering(3)).markedForDeleteAt());
        assertEquals(5, l.searchDeletionTime(clustering(4)).markedForDeleteAt());

        assertEquals(2, l.searchDeletionTime(clustering(5)).markedForDeleteAt());

        assertEquals(null, l.searchDeletionTime(clustering(7)));

        assertEquals(3, l.searchDeletionTime(clustering(14)).markedForDeleteAt());

        assertEquals(6, l.searchDeletionTime(clustering(15)).markedForDeleteAt());
        assertEquals(null, l.searchDeletionTime(clustering(18)));
    }