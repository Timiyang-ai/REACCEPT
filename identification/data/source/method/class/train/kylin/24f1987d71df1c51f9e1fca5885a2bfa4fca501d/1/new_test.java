    @Test
    public void flatFilterTest() {
        TupleFilter topAndFilter = new LogicalTupleFilter(TupleFilter.FilterOperatorEnum.AND);
        topAndFilter.addChild(createEQFilter("c1", "v1"));
        TupleFilter orFilter1 = new LogicalTupleFilter(FilterOperatorEnum.OR);
        TupleFilter andFilter11 = new LogicalTupleFilter(FilterOperatorEnum.AND);
        andFilter11.addChild(createEQFilter("c2", "v2"));
        andFilter11.addChild(createEQFilter("c3", "v3"));

        TupleFilter andFilter12 = new LogicalTupleFilter(FilterOperatorEnum.AND);
        andFilter12.addChild(createEQFilter("c2", "v21"));
        andFilter12.addChild(createEQFilter("c3", "v31"));

        orFilter1.addChild(andFilter11);
        orFilter1.addChild(andFilter12);

        TupleFilter orFilter2 = new LogicalTupleFilter(FilterOperatorEnum.OR);
        TupleFilter andFilter21 = new LogicalTupleFilter(FilterOperatorEnum.AND);
        andFilter21.addChild(createEQFilter("c4", "v4"));
        andFilter21.addChild(createEQFilter("c5", "v5"));

        TupleFilter andFilter22 = new LogicalTupleFilter(FilterOperatorEnum.AND);
        andFilter22.addChild(createEQFilter("c4", "v41"));
        andFilter22.addChild(createEQFilter("c5", "v51"));

        TupleFilter andFilter23 = new LogicalTupleFilter(FilterOperatorEnum.AND);
        andFilter23.addChild(createEQFilter("c4", "v42"));
        andFilter23.addChild(createEQFilter("c5", "v52"));

        orFilter2.addChild(andFilter21);
        orFilter2.addChild(andFilter22);
        orFilter2.addChild(andFilter23);

        topAndFilter.addChild(orFilter1);
        topAndFilter.addChild(orFilter2);

        TupleFilter flatFilter = topAndFilter.flatFilter(500000);
        Assert.assertEquals(6, flatFilter.children.size());
    }