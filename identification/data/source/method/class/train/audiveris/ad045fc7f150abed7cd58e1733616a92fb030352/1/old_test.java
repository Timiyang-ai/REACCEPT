    @Test
    public void findPoint ()
    {
        Point expResult = p2;
        System.out.println("findPoint " + expResult);
        System.out.println("before: " + instance.getSequenceString());

        Point point = new Point(11, 2);
        assertEquals(p1, instance.findPoint(point));
    }