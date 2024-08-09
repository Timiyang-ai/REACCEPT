@Test
    public void testRemoveFirstOccurrence()
    {
        int modulo = 10;
        int count = 10000;
        sequence.clear();
        for (int i = 0; i < count; i++)
        {
            deque.addLast(/* intrinsic:ktypecast */ (i % modulo));
            sequence.add( /* intrinsic:ktypecast */ (i % modulo));
        }

        Random rnd = new Random(0x11223344);
        for (int i = 0; i < 500; i++)
        {
            /* replaceIf:primitiveKType KType */ Object /* end:replaceIf */ k = 
                /* intrinsic:ktypecast */ rnd.nextInt(modulo);
            assertEquals(deque.removeFirstOccurrence(k), sequence.removeFirstOccurrence(k) >= 0);
        }

        assertListEquals(deque.toArray(), sequence.toArray());

        assertFalse(deque.removeFirstOccurrence(/* intrinsic:ktypecast */ (modulo + 1)));
        deque.addLast(/* intrinsic:ktypecast */ (modulo + 1));
        assertTrue(deque.removeFirstOccurrence(/* intrinsic:ktypecast */ (modulo + 1)));
    }