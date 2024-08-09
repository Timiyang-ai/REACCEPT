@Test
    public void findPoint ()
    {
        Point expResult = p2;
        System.out.println("findPoint " + expResult);
        System.out.println("before: " + instance.getSequenceString());

        Point point = new Point(11, 2);
        instance.setStickyDistance(1);

        Point result = instance.findPoint(point);
        assertEquals(expResult, result);

        instance.setStickyDistance(0);
        assertEquals(null, instance.findPoint(point));
    }