    @Test
    public void format_shouldNotFailWhenAllParametersAreNull() {
        Assert.assertEquals("",Format.format(null, null, null));
    }