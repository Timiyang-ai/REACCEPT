@Test
    public void testRemoveFirstOccurrence()
    {
        int modulo = 10;
        int count = 10000;
        sequence.clear();
        for (int i = 0; i < count; i++)
        {
            deque.addLast(cast(i % modulo));
            sequence.add(cast(i % modulo));
        }

        Random rnd = getRandom();
        for (int i = 0; i < 500; i++)
        {
            KType k = cast(rnd.nextInt(modulo));
            assertEquals(
                deque.removeFirstOccurrence(k) >= 0, 
                sequence.removeFirstOccurrence(k) >= 0);
        }

        assertListEquals(deque.toArray(), sequence.toArray());

        assertTrue(0 > deque.removeFirstOccurrence(cast(modulo + 1)));
        deque.addLast(cast(modulo + 1));
        assertTrue(0 <= deque.removeFirstOccurrence(cast(modulo + 1)));
    }