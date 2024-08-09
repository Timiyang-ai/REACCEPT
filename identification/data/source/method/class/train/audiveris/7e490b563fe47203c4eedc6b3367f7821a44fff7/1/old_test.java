    @Test
    public void removePoint ()
    {
        System.out.println("removePoint " + p2);
        System.out.println("before: " + instance.getSequenceString());

        instance.removePoint(p2);
        System.out.println("after : " + instance.getSequenceString());
        assertTrue(p3.equals(instance.getPoint(2)));
    }