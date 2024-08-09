public static PiPipelineModel parse(URL p4InfoUrl) throws P4InfoParserException {

        final P4Info p4info;
        try {
            p4info = getP4InfoMessage(p4InfoUrl);
        } catch (IOException e) {
            throw new P4InfoParserException("Unable to parse protobuf " + p4InfoUrl.toString(), e);
        }

        // Start by parsing and mapping instances to to their integer P4Info IDs.
        // Convenient to build the table model at the end.

        // Counters.
        final Map<Integer, PiCounterModel> counterMap = Maps.newHashMap();
        counterMap.putAll(parseCounters(p4info));
        counterMap.putAll(parseDirectCounters(p4info));

        // Meters.
        final Map<Integer, PiMeterModel> meterMap = Maps.newHashMap();
        meterMap.putAll(parseMeters(p4info));
        meterMap.putAll(parseDirectMeters(p4info));

        // Registers.
        final Map<Integer, PiRegisterModel> registerMap = Maps.newHashMap();
        registerMap.putAll(parseRegisters(p4info));

        // Action profiles.
        final Map<Integer, PiActionProfileModel> actProfileMap = parseActionProfiles(p4info);

        // Actions.
        final Map<Integer, PiActionModel> actionMap = parseActions(p4info);

        // Controller packet metadatas.
        final Map<PiPacketOperationType, PiPacketOperationModel> pktOpMap = parseCtrlPktMetadatas(p4info);

        // Finally, parse tables.
        final ImmutableMap.Builder<PiTableId, PiTableModel> tableImmMapBuilder =
                ImmutableMap.builder();
        for (Table tableMsg : p4info.getTablesList()) {
            final PiTableId tableId = PiTableId.of(tableMsg.getPreamble().getName());
            // Parse match fields.
            final ImmutableMap.Builder<PiMatchFieldId, PiMatchFieldModel> tableFieldMapBuilder =
                    ImmutableMap.builder();
            for (MatchField fieldMsg : tableMsg.getMatchFieldsList()) {
                final PiMatchFieldId fieldId = PiMatchFieldId.of(fieldMsg.getName());
                tableFieldMapBuilder.put(
                        fieldId,
                        new P4MatchFieldModel(fieldId,
                                              fieldMsg.getBitwidth(),
                                              mapMatchFieldType(fieldMsg.getMatchType())));

            }
            // Retrieve action models by inter IDs.
            final ImmutableMap.Builder<PiActionId, PiActionModel> tableActionMapBuilder =
                    ImmutableMap.builder();
            tableMsg.getActionRefsList().stream()
                    .map(ActionRef::getId)
                    .map(actionMap::get)
                    .forEach(actionModel -> tableActionMapBuilder.put(actionModel.id(), actionModel));
            // Retrieve direct meters by integer IDs.
            final ImmutableMap.Builder<PiMeterId, PiMeterModel> tableMeterMapBuilder =
                    ImmutableMap.builder();
            tableMsg.getDirectResourceIdsList()
                    .stream()
                    .map(meterMap::get)
                    // Direct resource ID might be that of a counter.
                    // Filter out missed mapping.
                    .filter(Objects::nonNull)
                    .forEach(meterModel -> tableMeterMapBuilder.put(meterModel.id(), meterModel));
            // Retrieve direct counters by integer IDs.
            final ImmutableMap.Builder<PiCounterId, PiCounterModel> tableCounterMapBuilder =
                    ImmutableMap.builder();
            tableMsg.getDirectResourceIdsList()
                    .stream()
                    .map(counterMap::get)
                    // As before, resource ID might be that of a meter.
                    // Filter out missed mapping.
                    .filter(Objects::nonNull)
                    .forEach(counterModel -> tableCounterMapBuilder.put(counterModel.id(), counterModel));
            tableImmMapBuilder.put(
                    tableId,
                    new P4TableModel(
                            PiTableId.of(tableMsg.getPreamble().getName()),
                            tableMsg.getImplementationId() == 0 ? PiTableType.DIRECT : PiTableType.INDIRECT,
                            actProfileMap.get(tableMsg.getImplementationId()),
                            tableMsg.getSize(),
                            tableCounterMapBuilder.build(),
                            tableMeterMapBuilder.build(),
                            tableMsg.getWithEntryTimeout(),
                            tableFieldMapBuilder.build(),
                            tableActionMapBuilder.build(),
                            actionMap.get(tableMsg.getConstDefaultActionId()),
                            tableMsg.getConstDefaultActionHasMutableParams()));

        }

        // Get a map with proper PI IDs for some of those maps we created at the beginning.
        ImmutableMap<PiCounterId, PiCounterModel> counterImmMap = ImmutableMap.copyOf(
                counterMap.values().stream()
                        .collect(Collectors.toMap(PiCounterModel::id, c -> c)));
        ImmutableMap<PiMeterId, PiMeterModel> meterImmMap = ImmutableMap.copyOf(
                meterMap.values().stream()
                        .collect(Collectors.toMap(PiMeterModel::id, m -> m)));
        ImmutableMap<PiRegisterId, PiRegisterModel> registerImmMap = ImmutableMap.copyOf(
                registerMap.values().stream()
                        .collect(Collectors.toMap(PiRegisterModel::id, r -> r)));
        ImmutableMap<PiActionProfileId, PiActionProfileModel> actProfileImmMap = ImmutableMap.copyOf(
                actProfileMap.values().stream()
                        .collect(Collectors.toMap(PiActionProfileModel::id, a -> a)));

        return new P4PipelineModel(
                tableImmMapBuilder.build(),
                counterImmMap,
                meterImmMap,
                registerImmMap,
                actProfileImmMap,
                ImmutableMap.copyOf(pktOpMap));
    }