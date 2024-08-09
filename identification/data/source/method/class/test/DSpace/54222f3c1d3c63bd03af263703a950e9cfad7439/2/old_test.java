@Test
    public void testCompare() 
    {
        int result = 0;
        ItemComparator ic = null;

        //one of the tiems has no value
        ic = new ItemComparator("test", "one", Item.ANY, true);
        result = ic.compare(one, two);
        assertTrue("testCompare 0",result == 0);

        ic = new ItemComparator("test", "one", Item.ANY, true);
        one.addMetadata("dc", "test", "one", Item.ANY, "1");        
        result = ic.compare(one, two);
        assertTrue("testCompare 1",result >= 1);
        one.clearMetadata("dc", "test", "one", Item.ANY);
        
        ic = new ItemComparator("test", "one", Item.ANY, true);
        two.addMetadata("dc", "test", "one", Item.ANY, "1");        
        result = ic.compare(one, two);
        assertTrue("testCompare 2",result <= -1);
        two.clearMetadata("dc", "test", "one", Item.ANY);
        
        //value in both items
        ic = new ItemComparator("test", "one", Item.ANY, true);
        one.addMetadata("dc", "test", "one", Item.ANY, "1");
        two.addMetadata("dc", "test", "one", Item.ANY, "2");
        result = ic.compare(one, two);
        assertTrue("testCompare 3",result <= -1);
        one.clearMetadata("dc", "test", "one", Item.ANY);
        two.clearMetadata("dc", "test", "one", Item.ANY);
        
        ic = new ItemComparator("test", "one", Item.ANY, true);
        one.addMetadata("dc", "test", "one", Item.ANY, "1");
        two.addMetadata("dc", "test", "one", Item.ANY, "1");
        result = ic.compare(one, two);
        assertTrue("testCompare 4",result == 0);
        one.clearMetadata("dc", "test", "one", Item.ANY);
        two.clearMetadata("dc", "test", "one", Item.ANY);
        
        ic = new ItemComparator("test", "one", Item.ANY, true);
        one.addMetadata("dc", "test", "one", Item.ANY, "2");
        two.addMetadata("dc", "test", "one", Item.ANY, "1");
        result = ic.compare(one, two);
        assertTrue("testCompare 5",result >= 1);
        one.clearMetadata("dc", "test", "one", Item.ANY);
        two.clearMetadata("dc", "test", "one", Item.ANY);

        //multiple values (min, max)
        ic = new ItemComparator("test", "one", Item.ANY, true);
        one.addMetadata("dc", "test", "one", Item.ANY, "0");
        one.addMetadata("dc", "test", "one", Item.ANY, "1");
        two.addMetadata("dc", "test", "one", Item.ANY, "2");
        two.addMetadata("dc", "test", "one", Item.ANY, "3");
        result = ic.compare(one, two);
        assertTrue("testCompare 3",result <= -1);
        one.clearMetadata("dc", "test", "one", Item.ANY);
        two.clearMetadata("dc", "test", "one", Item.ANY);

        ic = new ItemComparator("test", "one", Item.ANY, true);
        one.addMetadata("dc", "test", "one", Item.ANY, "0");
        one.addMetadata("dc", "test", "one", Item.ANY, "1");
        two.addMetadata("dc", "test", "one", Item.ANY, "-1");
        two.addMetadata("dc", "test", "one", Item.ANY, "1");
        result = ic.compare(one, two);
        assertTrue("testCompare 4",result == 0);
        one.clearMetadata("dc", "test", "one", Item.ANY);
        two.clearMetadata("dc", "test", "one", Item.ANY);

        ic = new ItemComparator("test", "one", Item.ANY, true);
        one.addMetadata("dc", "test", "one", Item.ANY, "1");
        one.addMetadata("dc", "test", "one", Item.ANY, "2");
        two.addMetadata("dc", "test", "one", Item.ANY, "1");
        two.addMetadata("dc", "test", "one", Item.ANY, "-1");
        result = ic.compare(one, two);
        assertTrue("testCompare 5",result >= 1);
        one.clearMetadata("dc", "test", "one", Item.ANY);
        two.clearMetadata("dc", "test", "one", Item.ANY);

        ic = new ItemComparator("test", "one", Item.ANY, false);
        one.addMetadata("dc", "test", "one", Item.ANY, "1");
        one.addMetadata("dc", "test", "one", Item.ANY, "2");
        two.addMetadata("dc", "test", "one", Item.ANY, "2");
        two.addMetadata("dc", "test", "one", Item.ANY, "3");
        result = ic.compare(one, two);
        assertTrue("testCompare 3",result <= -1);
        one.clearMetadata("dc", "test", "one", Item.ANY);
        two.clearMetadata("dc", "test", "one", Item.ANY);

        ic = new ItemComparator("test", "one", Item.ANY, false);
        one.addMetadata("dc", "test", "one", Item.ANY, "1");
        one.addMetadata("dc", "test", "one", Item.ANY, "2");
        two.addMetadata("dc", "test", "one", Item.ANY, "1");
        two.addMetadata("dc", "test", "one", Item.ANY, "5");
        result = ic.compare(one, two);
        assertTrue("testCompare 4",result == 0);
        one.clearMetadata("dc", "test", "one", Item.ANY);
        two.clearMetadata("dc", "test", "one", Item.ANY);

        ic = new ItemComparator("test", "one", Item.ANY, false);
        one.addMetadata("dc", "test", "one", Item.ANY, "2");
        one.addMetadata("dc", "test", "one", Item.ANY, "3");
        two.addMetadata("dc", "test", "one", Item.ANY, "1");
        two.addMetadata("dc", "test", "one", Item.ANY, "4");
        result = ic.compare(one, two);
        assertTrue("testCompare 5",result >= 1);
        one.clearMetadata("dc", "test", "one", Item.ANY);
        two.clearMetadata("dc", "test", "one", Item.ANY);
    }