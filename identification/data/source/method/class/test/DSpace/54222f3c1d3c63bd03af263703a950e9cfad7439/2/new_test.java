@Test
    public void testCompare() throws SQLException {
        int result = 0;
        ItemComparator ic = null;

        //one of the tiems has no value
        ic = new ItemComparator("test", "one", Item.ANY, true);
        result = ic.compare(one, two);
        assertTrue("testCompare 0",result == 0);

        ic = new ItemComparator("test", "one", Item.ANY, true);
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "1");
        result = ic.compare(one, two);
        assertTrue("testCompare 1",result >= 1);
        itemService.clearMetadata(context, one, "dc", "test", "one", Item.ANY);
        
        ic = new ItemComparator("test", "one", Item.ANY, true);
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "1");        
        result = ic.compare(one, two);
        assertTrue("testCompare 2",result <= -1);
        itemService.clearMetadata(context, two, "dc", "test", "one", Item.ANY);
        
        //value in both items
        ic = new ItemComparator("test", "one", Item.ANY, true);
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "1");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "2");
        result = ic.compare(one, two);
        assertTrue("testCompare 3",result <= -1);
        itemService.clearMetadata(context, one, "dc", "test", "one", Item.ANY);
        itemService.clearMetadata(context, two, "dc", "test", "one", Item.ANY);
        
        ic = new ItemComparator("test", "one", Item.ANY, true);
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "1");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "1");
        result = ic.compare(one, two);
        assertTrue("testCompare 4",result == 0);
        itemService.clearMetadata(context, one, "dc", "test", "one", Item.ANY);
        itemService.clearMetadata(context, two, "dc", "test", "one", Item.ANY);
        
        ic = new ItemComparator("test", "one", Item.ANY, true);
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "2");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "1");
        result = ic.compare(one, two);
        assertTrue("testCompare 5",result >= 1);
        itemService.clearMetadata(context, one, "dc", "test", "one", Item.ANY);
        itemService.clearMetadata(context, two, "dc", "test", "one", Item.ANY);

        //multiple values (min, max)
        ic = new ItemComparator("test", "one", Item.ANY, true);
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "0");
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "1");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "2");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "3");
        result = ic.compare(one, two);
        assertTrue("testCompare 3",result <= -1);
        itemService.clearMetadata(context, one, "dc", "test", "one", Item.ANY);
        itemService.clearMetadata(context, two, "dc", "test", "one", Item.ANY);

        ic = new ItemComparator("test", "one", Item.ANY, true);
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "0");
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "1");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "-1");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "1");
        result = ic.compare(one, two);
        assertTrue("testCompare 4",result == 0);
        itemService.clearMetadata(context, one, "dc", "test", "one", Item.ANY);
        itemService.clearMetadata(context, two, "dc", "test", "one", Item.ANY);

        ic = new ItemComparator("test", "one", Item.ANY, true);
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "1");
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "2");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "1");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "-1");
        result = ic.compare(one, two);
        assertTrue("testCompare 5",result >= 1);
        itemService.clearMetadata(context, one, "dc", "test", "one", Item.ANY);
        itemService.clearMetadata(context, two, "dc", "test", "one", Item.ANY);

        ic = new ItemComparator("test", "one", Item.ANY, false);
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "1");
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "2");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "2");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "3");
        result = ic.compare(one, two);
        assertTrue("testCompare 3",result <= -1);
        itemService.clearMetadata(context, one, "dc", "test", "one", Item.ANY);
        itemService.clearMetadata(context, two, "dc", "test", "one", Item.ANY);

        ic = new ItemComparator("test", "one", Item.ANY, false);
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "1");
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "2");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "1");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "5");
        result = ic.compare(one, two);
        assertTrue("testCompare 4",result == 0);
        itemService.clearMetadata(context, one, "dc", "test", "one", Item.ANY);
        itemService.clearMetadata(context, two, "dc", "test", "one", Item.ANY);

        ic = new ItemComparator("test", "one", Item.ANY, false);
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "2");
        itemService.addMetadata(context, one, "dc", "test", "one", Item.ANY, "3");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "1");
        itemService.addMetadata(context, two, "dc", "test", "one", Item.ANY, "4");
        result = ic.compare(one, two);
        assertTrue("testCompare 5",result >= 1);
        itemService.clearMetadata(context, one, "dc", "test", "one", Item.ANY);
        itemService.clearMetadata(context, two, "dc", "test", "one", Item.ANY);
    }