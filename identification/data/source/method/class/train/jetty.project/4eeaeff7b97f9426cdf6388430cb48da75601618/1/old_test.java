@Test
    public void testRemove()
    {
        m0.remove("abc");
        m1.remove("abc");
        m5.remove("aBc");
        m5.remove("bbb");
        m5i.remove("aBc");
        m5i.remove(null);

        assertEquals(0, m0.size());
        assertEquals(0, m1.size());
        assertEquals(4, m5.size());
        assertEquals(3, m5i.size());

        assertEquals("2",m5.get("abc"));
        assertEquals(null,m5.get("bbb"));
        assertEquals(null,m5i.get("AbC"));
        assertEquals(null,m5i.get(null));
    }