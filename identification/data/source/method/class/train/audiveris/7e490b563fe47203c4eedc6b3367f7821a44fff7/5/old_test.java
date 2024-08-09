    @Test
    public void insertPointAfter ()
    {
        System.out.println("insertPointAfter " + p2);
        System.out.println("before: " + instance.getSequenceString());

        Point point = new Point(5, 2);
        instance.insertPointAfter(point, p2);
        System.out.println("after : " + instance.getSequenceString());
        assertEquals(3, instance.indexOf(point));
    }