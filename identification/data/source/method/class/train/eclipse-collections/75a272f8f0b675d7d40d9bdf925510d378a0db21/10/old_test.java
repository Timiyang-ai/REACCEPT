    @Test
    public void select()
    {
        ArrayList<Integer> list = this.getIntegerList();
        ArrayList<Integer> results = ArrayListIterate.select(list, Integer.class::isInstance);
        Verify.assertSize(5, results);
    }