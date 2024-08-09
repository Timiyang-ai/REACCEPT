    @Test
    void createValidator() throws RedPenException {
        Configuration conf = Configuration.builder().addValidatorConfig(new ValidatorConfiguration("SentenceLength")).build();
        assertEquals(SentenceLengthValidator.class, ValidatorFactory.getInstance(conf.getValidatorConfigs().get(0), conf).getClass());
    }