@Test
    public void findSegment ()
    {
        System.out.println("findSegment");
        System.out.println("before: " + instance.getSequenceString());

        Point point = new Point(11, 3);
        assertEquals(p1, instance.findSegment(point));

        instance = new BrokenLine();
        assertEquals(null, instance.findSegment(point));
        instance.addPoint(p1);
        assertEquals(null, instance.findSegment(point));
        instance.addPoint(p2);
        assertEquals(p1, instance.findSegment(point));
    }