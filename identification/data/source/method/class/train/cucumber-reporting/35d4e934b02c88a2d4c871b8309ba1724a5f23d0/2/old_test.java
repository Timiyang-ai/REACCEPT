    @Test
    public void getPercentageResult_Returns0Percent() {

        // given
        // from @Before

        // when
        String percentage = stepObject.getPercentageResult();

        // then
        assertThat(percentage).isEqualTo("33.33%");
    }