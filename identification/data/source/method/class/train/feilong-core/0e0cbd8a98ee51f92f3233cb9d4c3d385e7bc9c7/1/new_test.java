@Test
    @Repeat(20000)
    public void testCreateRandom2(){
        assertThat(RandomUtil.createRandom(10, 20), allOf(greaterThanOrEqualTo(10L), lessThan(20L)));
        assertThat(RandomUtil.createRandom(0, 800), allOf(greaterThanOrEqualTo(0L), lessThan(800L)));
    }