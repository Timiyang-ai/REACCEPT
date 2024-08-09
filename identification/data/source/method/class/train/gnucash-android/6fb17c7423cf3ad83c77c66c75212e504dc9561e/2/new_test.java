    @Test
    public void setSmallestFraction_shouldNotUseDigits(){
        Commodity commodity = new Commodity("Test", "USD", 100);
        assertThat(commodity.getSmallestFraction()).isEqualTo(100);

        commodity.setSmallestFraction(1000);
        assertThat(commodity.getSmallestFraction()).isEqualTo(1000);
    }