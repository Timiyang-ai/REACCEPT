@Test
    public void testEnsureCapacity()
    {
        TightRandomResizingStrategy resizer = new TightRandomResizingStrategy();
        KTypeArrayDeque<KType> deque = new KTypeArrayDeque<>(0, resizer);

        // Add some elements.
        final int max = rarely() ? 0 : randomIntBetween(0, 1000);
        for (int i = 0; i < max; i++) {
          deque.addLast(cast(i));
        }

        final int additions = randomIntBetween(0, 5000);
        deque.ensureCapacity(additions + deque.size());
        final int before = resizer.growCalls;
        for (int i = 0; i < additions; i++) {
          if (randomBoolean()) { 
            deque.addLast(cast(i));
          } else {
            deque.addFirst(cast(i));
          }
        }
        assertEquals(before, resizer.growCalls);
    }