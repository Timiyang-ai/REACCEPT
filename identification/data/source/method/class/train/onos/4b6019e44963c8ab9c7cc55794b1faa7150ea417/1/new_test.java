@Test
    public void matchMplsLabelTest() {
        Criterion criterion = Criteria.matchMplsLabel(MplsLabel.mplsLabel(0xffffe));
        ObjectNode result = criterionCodec.encode(criterion, context);
        assertThat(result, matchesCriterion(criterion));
    }