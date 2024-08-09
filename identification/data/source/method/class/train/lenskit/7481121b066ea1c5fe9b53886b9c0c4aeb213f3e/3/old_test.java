@Test
    public void testGet() {
        try {
            emptyVector().get(5);
            fail("invalid key should throw exception");
        } catch (IllegalArgumentException e) { /* expected */ }

        SparseVector v = singleton();
        assertThat(v.get(5), closeTo(Math.PI));
        try {
            v.get(2);
            fail("should throw IllegalArgumentException for bad argument");
        } catch (IllegalArgumentException e) { /* expected */ }

        v = simpleVector();
        assertThat(v.get(7), closeTo(3.5));
        assertThat(v.get(7), closeTo(3.5));
        assertThat(v.get(3), closeTo(1.5));
        assertThat(v.get(8), closeTo(2));
    }