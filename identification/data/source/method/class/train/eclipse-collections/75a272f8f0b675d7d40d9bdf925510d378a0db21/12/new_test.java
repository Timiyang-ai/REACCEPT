    @Test
    public void reverse()
    {
        MutableList<String> list = FastList.newListWith("1", "4", "2", "3");
        Assert.assertEquals(
                FastList.newListWith("4", "3", "2", "1"),
                list.sortThis(Comparators.reverse(String::compareTo)));
        Verify.assertThrows(NullPointerException.class, () -> Comparators.reverse(null));
    }