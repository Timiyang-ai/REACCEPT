    @Test
    public void isNotEmpty() {
        Assert.assertFalse(CollectionUtils.hasLength(null));
        Assert.assertFalse(CollectionUtils.hasLength(Collections.emptyList()));
    }