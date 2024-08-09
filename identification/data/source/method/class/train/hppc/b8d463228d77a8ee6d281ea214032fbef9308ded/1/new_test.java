@Test
    public void testRemoveLastOccurrence()
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
            assertEquals(
                deque.removeLastOccurrence(k) >= 0, 
                sequence.removeLastOccurrence(k) >= 0);
        }

        assertListEquals(deque.toArray(), sequence.toArray());

        assertTrue(0 > deque.removeLastOccurrence(/* intrinsic:ktypecast */ (modulo + 1)));
        deque.addFirst(/* intrinsic:ktypecast */ (modulo + 1));
        assertTrue(0 <= deque.removeLastOccurrence(/* intrinsic:ktypecast */ (modulo + 1)));
    }