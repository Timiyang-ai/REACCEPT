    @Test
    public void addWhere_onIncorrectQuery() throws RecognitionException {
        DomainModel model = prepareDomainModel();

        try {
            QueryTransformerAstBased transformer = new QueryTransformerAstBased(model,
                    "select h from sec$GroupHierarchy h join h.parent.constraints");
            transformer.getResult();
            fail("Not named join variable passed");
        } catch (JpqlSyntaxException e) {
            //expected
        }
    }