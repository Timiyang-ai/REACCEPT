    @Test
    public void formatAsPercentage_ReturnsFormattedValue() {

        // given
        final int[][] values = {{1, 3}, {2, 2}, {1, 5}, {0, 5}};
        String[] formatted = {"33.33%", "100.00%", "20.00%", "0.00%"};

        // then
        for (int i = 0; i < values.length; i++) {
            assertThat(Util.formatAsPercentage(values[i][0], values[i][1])).isEqualTo(formatted[i]);
        }
    }