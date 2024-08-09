    @Test
    public void toString_shouldUseDefaultLocale() {
        Locale.setDefault(Locale.GERMANY);

        String exchangeRateString = "1.234";
        BigDecimal exchangeRate = new BigDecimal(exchangeRateString);
        Price price = new Price("commodity1UID", "commodity2UID", exchangeRate);
        assertThat(price.toString()).isEqualTo("1,234");
    }