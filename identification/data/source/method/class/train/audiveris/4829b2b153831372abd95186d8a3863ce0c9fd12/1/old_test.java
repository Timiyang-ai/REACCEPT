@Test
    public void findSegment ()
    {
        System.out.println("findSegment");
        System.out.println("before: " + instance.getSequenceString());

        Point point = new Point(11, 3);
        instance.setStickyDistance(2);
        assertEquals(p1, instance.findSegment(point));

        instance.setStickyDistance(0);
        assertEquals(null, instance.findSegment(point));

        instance = new BrokenLine();
        instance.setStickyDistance(2);
        assertEquals(null, instance.findSegment(point));
        instance.addPoint(p1);
        assertEquals(null, instance.findSegment(point));
        instance.addPoint(p2);
        assertEquals(p1, instance.findSegment(point));
    }