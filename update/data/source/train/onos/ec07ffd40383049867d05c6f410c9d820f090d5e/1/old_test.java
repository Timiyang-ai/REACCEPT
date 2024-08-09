@Test
    public void testCompile() {
        sut.activate();

        List<Intent> compiled = sut.compile(intent, Collections.emptyList(), Collections.emptySet());
        assertThat(compiled, hasSize(1));

        Collection<FlowRule> rules = ((FlowRuleIntent) compiled.get(0)).flowRules();

        FlowRule rule1 = rules.stream()
                .filter(x -> x.deviceId().equals(d1p0.deviceId()))
                .findFirst()
                .get();
        verifyIdAndPriority(rule1, d1p0.deviceId());
        assertThat(rule1.selector(),
                is(DefaultTrafficSelector.builder(selector).matchInPort(d1p0.port()).build()));
        assertThat(rule1.treatment(),
                is(DefaultTrafficTreatment.builder().setOutput(d1p1.port()).build()));


        FlowRule rule2 = rules.stream()
                .filter(x -> x.deviceId().equals(d2p0.deviceId()))
                .findFirst()
                .get();
        verifyIdAndPriority(rule2, d2p0.deviceId());
        assertThat(rule2.selector(),
                is(DefaultTrafficSelector.builder(selector).matchInPort(d2p0.port()).build()));
        assertThat(rule2.treatment(),
                is(DefaultTrafficTreatment.builder().setOutput(d2p1.port()).build()));

        FlowRule rule3 = rules.stream()
                .filter(x -> x.deviceId().equals(d3p0.deviceId()))
                .findFirst()
                .get();
        verifyIdAndPriority(rule3, d3p1.deviceId());
        assertThat(rule3.selector(),
                is(DefaultTrafficSelector.builder(selector).matchInPort(d3p1.port()).build()));
        assertThat(rule3.treatment(),
                is(DefaultTrafficTreatment.builder(treatment).setOutput(d3p0.port()).build()));

        sut.deactivate();
    }