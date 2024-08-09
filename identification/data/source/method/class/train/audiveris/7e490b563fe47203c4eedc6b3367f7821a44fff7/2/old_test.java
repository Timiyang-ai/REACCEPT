    @Test
    public void movePoint ()
    {
        System.out.println("movePoint " + p1);
        System.out.println("before: " + instance.getSequenceString());

        Point location = new Point(11, 6);
        p1.setLocation(location);

        System.out.println("after : " + instance.getSequenceString());
        assertEquals(location, instance.getPoint(1));
    }