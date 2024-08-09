    @Test
    public void addPoint ()
    {
        System.out.println("addPoint");
        System.out.println("before: " + instance.getSequenceString());

        Point point = new Point(2, 3);
        instance.addPoint(point);
        System.out.println("after : " + instance.getSequenceString());

        ///assertEquals(5, instance.size());
    }