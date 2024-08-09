@Test
    public void testParse() throws Exception {
        // Generate two PiPipelineModels from p4Info file
        PiPipelineModel model = P4InfoParser.parse(p4InfoUrl);
        PiPipelineModel model2 = P4InfoParser.parse(p4InfoUrl);

        // Check equality
        new EqualsTester().addEqualityGroup(model, model2).testEquals();

        // Generate a P4Info object from the file
        final P4Info p4info;
        try {
            p4info = getP4InfoMessage(p4InfoUrl);
        } catch (IOException e) {
            throw new P4InfoParserException("Unable to parse protobuf " + p4InfoUrl.toString(), e);
        }

        List<Table> tableMsgs =  p4info.getTablesList();
        PiTableId table0Id = PiTableId.of(tableMsgs.get(0).getPreamble().getName());
        PiTableId wcmpTableId = PiTableId.of(tableMsgs.get(1).getPreamble().getName());

        //parse tables
        PiTableModel table0Model = model.table(table0Id).orElse(null);
        PiTableModel wcmpTableModel = model.table(wcmpTableId).orElse(null);
        PiTableModel table0Model2 = model2.table(table0Id).orElse(null);
        PiTableModel wcmpTableModel2 = model2.table(wcmpTableId).orElse(null);

        new EqualsTester().addEqualityGroup(table0Model, table0Model2)
                .addEqualityGroup(wcmpTableModel, wcmpTableModel2).testEquals();

        // Check existence
        assertThat("model parsed value is null", table0Model, notNullValue());
        assertThat("model parsed value is null", wcmpTableModel, notNullValue());
        assertThat("Incorrect size for table0 size", table0Model.maxSize(), is(equalTo(DEFAULT_MAX_TABLE_SIZE)));
        assertThat("Incorrect size for wcmp_table size", wcmpTableModel.maxSize(), is(equalTo(DEFAULT_MAX_TABLE_SIZE)));

        // Check matchFields
        List<MatchField> matchFieldList = tableMsgs.get(0).getMatchFieldsList();
        List<PiMatchFieldModel> piMatchFieldList = new ArrayList<>();

        for (MatchField matchFieldIter : matchFieldList) {
            MatchField.MatchType matchType = matchFieldIter.getMatchType();
            PiMatchType piMatchType;
            switch (matchType) {
                case EXACT: piMatchType = PiMatchType.EXACT; break;
                case LPM: piMatchType = PiMatchType.LPM; break;
                case TERNARY: piMatchType = PiMatchType.TERNARY; break;
                case RANGE: piMatchType = PiMatchType.RANGE; break;
                default: Assert.fail(); return;
            }
            piMatchFieldList.add(new P4MatchFieldModel(PiMatchFieldId.of(matchFieldIter.getName()),
                                                       matchFieldIter.getBitwidth(), piMatchType));
        }
        // Check MatchFields size
        assertThat("Incorrect size for matchFields", table0Model.matchFields().size(), is(equalTo(9)));
        // Check if matchFields are in order
        assertThat("Incorrect order for matchFields", table0Model.matchFields(), IsIterableContainingInOrder.contains(
                piMatchFieldList.get(0), piMatchFieldList.get(1),
                piMatchFieldList.get(2), piMatchFieldList.get(3),
                piMatchFieldList.get(4), piMatchFieldList.get(5),
                piMatchFieldList.get(6), piMatchFieldList.get(7),
                piMatchFieldList.get(8)));

        assertThat("Incorrect size for matchFields", wcmpTableModel.matchFields().size(), is(equalTo(1)));

        // check if matchFields are in order
        matchFieldList = tableMsgs.get(1).getMatchFieldsList();
        assertThat("Incorrect order for matchFields",
                   wcmpTableModel.matchFields(), IsIterableContainingInOrder.contains(
                        new P4MatchFieldModel(PiMatchFieldId.of(matchFieldList.get(0).getName()),
                                              matchFieldList.get(0).getBitwidth(), PiMatchType.EXACT)));

        //check table0 actionsRefs
        List<ActionRef> actionRefList = tableMsgs.get(0).getActionRefsList();
        assertThat("Incorrect size for actionRefs", actionRefList.size(), is(equalTo(4)));

        //create action instances
        PiActionId actionId = PiActionId.of("set_egress_port");
        PiActionParamId piActionParamId = PiActionParamId.of("port");
        int bitWitdth = 9;
        PiActionParamModel actionParamModel = new P4ActionParamModel(piActionParamId, bitWitdth);
        ImmutableMap<PiActionParamId, PiActionParamModel> params = new
                ImmutableMap.Builder<PiActionParamId, PiActionParamModel>()
                .put(piActionParamId, actionParamModel).build();

        PiActionModel setEgressPortAction = new P4ActionModel(actionId, params);

        actionId = PiActionId.of("send_to_cpu");
        PiActionModel sendToCpuAction =
                new P4ActionModel(actionId, new ImmutableMap.Builder<PiActionParamId, PiActionParamModel>().build());

        actionId = PiActionId.of("_drop");
        PiActionModel dropAction =
                new P4ActionModel(actionId, new ImmutableMap.Builder<PiActionParamId, PiActionParamModel>().build());

        actionId = PiActionId.of("NoAction");
        PiActionModel noAction =
                new P4ActionModel(actionId, new ImmutableMap.Builder<PiActionParamId, PiActionParamModel>().build());

        actionId = PiActionId.of("table0_control.set_next_hop_id");
        piActionParamId = PiActionParamId.of("next_hop_id");
        bitWitdth = 16;
        actionParamModel = new P4ActionParamModel(piActionParamId, bitWitdth);
        params = new ImmutableMap.Builder<PiActionParamId, PiActionParamModel>()
                .put(piActionParamId, actionParamModel).build();

        PiActionModel setNextHopIdAction = new P4ActionModel(actionId, params);

        //check table0 actions
        assertThat("action dose not match",
                   table0Model.actions(), IsIterableContainingInAnyOrder.containsInAnyOrder(
                        setEgressPortAction, sendToCpuAction, setNextHopIdAction, dropAction));

        //check wcmp_table actions
        assertThat("actions dose not match",
                   wcmpTableModel.actions(), IsIterableContainingInAnyOrder.containsInAnyOrder(
                        setEgressPortAction, noAction));

        PiActionModel table0DefaultAction = table0Model.defaultAction().orElse(null);

        new EqualsTester().addEqualityGroup(table0DefaultAction, dropAction).testEquals();

        // Check existence
        assertThat("model parsed value is null", table0DefaultAction, notNullValue());

        //parse action profiles
        PiTableId tableId = PiTableId.of("wcmp_control.wcmp_table");
        ImmutableSet<PiTableId> tableIds = new ImmutableSet.Builder<PiTableId>().add(tableId).build();
        PiActionProfileId actionProfileId = PiActionProfileId.of("wcmp_control.wcmp_selector");
        PiActionProfileModel wcmpSelector3 = new P4ActionProfileModel(actionProfileId, tableIds,
                                                                      true, DEFAULT_MAX_ACTION_PROFILE_SIZE);
        PiActionProfileModel wcmpSelector = model.actionProfiles(actionProfileId).orElse(null);
        PiActionProfileModel wcmpSelector2 = model2.actionProfiles(actionProfileId).orElse(null);

        new EqualsTester().addEqualityGroup(wcmpSelector, wcmpSelector2, wcmpSelector3).testEquals();

        // Check existence
        assertThat("model parsed value is null", wcmpSelector, notNullValue());
        assertThat("Incorrect value for actions profiles", model.actionProfiles(), containsInAnyOrder(wcmpSelector));
        // ActionProfiles size
        assertThat("Incorrect size for action profiles", model.actionProfiles().size(), is(equalTo(1)));

        //parse counters
        PiCounterModel ingressPortCounterModel =
                model.counter(PiCounterId.of("port_counters_ingress.ingress_port_counter")).orElse(null);
        PiCounterModel egressPortCounterModel =
                model.counter(PiCounterId.of("port_counters_egress.egress_port_counter")).orElse(null);
        PiCounterModel table0CounterModel =
                model.counter(PiCounterId.of("table0_control.table0_counter")).orElse(null);
        PiCounterModel wcmpTableCounterModel =
                model.counter(PiCounterId.of("wcmp_control.wcmp_table_counter")).orElse(null);

        PiCounterModel ingressPortCounterModel2 =
                model2.counter(PiCounterId.of("port_counters_ingress.ingress_port_counter")).orElse(null);
        PiCounterModel egressPortCounterModel2 =
                model2.counter(PiCounterId.of("port_counters_egress.egress_port_counter")).orElse(null);
        PiCounterModel table0CounterModel2 =
                model2.counter(PiCounterId.of("table0_control.table0_counter")).orElse(null);
        PiCounterModel wcmpTableCounterModel2 =
                model2.counter(PiCounterId.of("wcmp_control.wcmp_table_counter")).orElse(null);

        new EqualsTester()
                .addEqualityGroup(ingressPortCounterModel, ingressPortCounterModel2)
                .addEqualityGroup(egressPortCounterModel, egressPortCounterModel2)
                .addEqualityGroup(table0CounterModel, table0CounterModel2)
                .addEqualityGroup(wcmpTableCounterModel, wcmpTableCounterModel2)
                .testEquals();

        assertThat("model parsed value is null", ingressPortCounterModel, notNullValue());
        assertThat("model parsed value is null", egressPortCounterModel, notNullValue());
        assertThat("model parsed value is null", table0CounterModel, notNullValue());
        assertThat("model parsed value is null", wcmpTableCounterModel, notNullValue());

        //Parse meters
        Collection<PiMeterModel> meterModel = model.meters();
        Collection<PiMeterModel> meterModel2 = model2.meters();

        assertThat("model pased meter collaction should be empty", meterModel.isEmpty(), is(true));
        assertThat("model pased meter collaction should be empty", meterModel2.isEmpty(), is(true));

        //parse packet operations
        PiPacketOperationModel packetInOperationalModel =
                model.packetOperationModel(PiPacketOperationType.PACKET_IN).orElse(null);
        PiPacketOperationModel packetOutOperationalModel =
                model.packetOperationModel(PiPacketOperationType.PACKET_OUT).orElse(null);

        PiPacketOperationModel packetInOperationalModel2 =
                model2.packetOperationModel(PiPacketOperationType.PACKET_IN).orElse(null);
        PiPacketOperationModel packetOutOperationalModel2 =
                model2.packetOperationModel(PiPacketOperationType.PACKET_OUT).orElse(null);

        new EqualsTester()
                .addEqualityGroup(packetInOperationalModel, packetInOperationalModel2)
                .addEqualityGroup(packetOutOperationalModel, packetOutOperationalModel2)
                .testEquals();

        // Check existence
        assertThat("model parsed value is null", packetInOperationalModel, notNullValue());
        assertThat("model parsed value is null", packetOutOperationalModel, notNullValue());
    }