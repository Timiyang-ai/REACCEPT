@Test
    public void apply() {
        final List<Integer> listA = new ArrayList<Integer>();
        listA.add(1);

        final List<Integer> listB = new ArrayList<Integer>();
        listB.add(2);

        final Closure<List<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<Integer>> col = new ArrayList<List<Integer>>();
        col.add(listA);
        col.add(listB);
        IterableUtils.apply(col, testClosure);
        assertTrue(listA.isEmpty() && listB.isEmpty());
        try {
            IterableUtils.apply(col, null);
            fail("expecting NullPointerException");
        } catch (NullPointerException npe) {
            // expected
        }

        IterableUtils.apply(null, testClosure);

        // null should be OK
        col.add(null);
        IterableUtils.apply(col, testClosure);
    }