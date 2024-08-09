@Test
    public void testGet() {
        assertThat(emptyVector().get(5), notANumber());
        SparseVector v = singleton();
        assertThat(v.get(5), closeTo(Math.PI));
        assertThat(v.get(2), notANumber());

        v = simpleVector();
        assertThat(v.get(7), closeTo(3.5));
        assertThat(v.get(7), closeTo(3.5));
        assertThat(v.get(3), closeTo(1.5));
        assertThat(v.get(8), closeTo(2));
    }