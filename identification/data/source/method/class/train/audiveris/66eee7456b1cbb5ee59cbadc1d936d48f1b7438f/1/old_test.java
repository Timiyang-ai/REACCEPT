    @Test
    public void isColinear ()
    {
        System.out.println("isColinear");
        instance.insertPointAfter(new Point(0, 1), p3);
        assertTrue(instance.isColinear(p3));
        assertFalse(instance.isColinear(p2));
    }