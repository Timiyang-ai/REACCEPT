    @Test
    public void reject()
    {
        ArrayList<Integer> list = this.getIntegerList();
        ArrayList<Integer> results = ArrayListIterate.reject(list, Integer.class::isInstance);
        Verify.assertEmpty(results);
    }