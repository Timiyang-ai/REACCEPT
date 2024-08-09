    @Test
    public void insertPoint ()
    {
        int index = 2;
        System.out.println("insertPoint " + index);
        System.out.println("before: " + instance.getSequenceString());

        Point point = new Point(5, 2);
        instance.insertPoint(index, point);
        System.out.println("after : " + instance.getSequenceString());
        assertTrue(point.equals(instance.getPoint(index)));
    }