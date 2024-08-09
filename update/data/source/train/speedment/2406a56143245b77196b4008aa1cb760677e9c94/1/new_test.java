@Test
    public void of() {
        final Random random = new Random();
        final Integer[] array = new Integer[100];
        for (int i = 0; i < 100; i++) {
            array[i] = random.nextInt();
        }

        final Tuple tuple = Tuples.ofArray((Object[]) array);
        assertEquals(tuple.degree(), 100);

        for (int i = 0; i < 100; i++) {
            final Integer expected = array[i];
            final Integer actual = (Integer) tuple.get(i);
            assertEquals(expected, actual);
        }
    }