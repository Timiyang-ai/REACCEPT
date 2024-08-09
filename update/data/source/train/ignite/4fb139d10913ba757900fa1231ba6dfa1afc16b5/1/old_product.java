ClientCacheConfiguration cacheConfiguration(BinaryInputStream in, ProtocolVersion ver)
        throws IOException {
        try (BinaryReaderExImpl reader = new BinaryReaderExImpl(marsh.context(), in, null, true)) {
            reader.readInt(); // Do not need length to read data. The protocol defines fixed configuration layout.

            return new ClientCacheConfiguration().setName("TBD") // cache name is to be assigned later
                .setAtomicityMode(CacheAtomicityMode.fromOrdinal(reader.readInt()))
                .setBackups(reader.readInt())
                .setCacheMode(CacheMode.fromOrdinal(reader.readInt()))
                .setCopyOnRead(reader.readBoolean())
                .setDataRegionName(reader.readString())
                .setEagerTtl(reader.readBoolean())
                .setStatisticsEnabled(reader.readBoolean())
                .setGroupName(reader.readString())
                .setDefaultLockTimeout(reader.readLong())
                .setMaxConcurrentAsyncOperations(reader.readInt())
                .setMaxQueryIteratorsCount(reader.readInt())
                .setName(reader.readString())
                .setOnheapCacheEnabled(reader.readBoolean())
                .setPartitionLossPolicy(PartitionLossPolicy.fromOrdinal((byte)reader.readInt()))
                .setQueryDetailMetricsSize(reader.readInt())
                .setQueryParallelism(reader.readInt())
                .setReadFromBackup(reader.readBoolean())
                .setRebalanceBatchSize(reader.readInt())
                .setRebalanceBatchesPrefetchCount(reader.readLong())
                .setRebalanceDelay(reader.readLong())
                .setRebalanceMode(CacheRebalanceMode.fromOrdinal(reader.readInt()))
                .setRebalanceOrder(reader.readInt())
                .setRebalanceThrottle(reader.readLong())
                .setRebalanceTimeout(reader.readLong())
                .setSqlEscapeAll(reader.readBoolean())
                .setSqlIndexMaxInlineSize(reader.readInt())
                .setSqlSchema(reader.readString())
                .setWriteSynchronizationMode(CacheWriteSynchronizationMode.fromOrdinal(reader.readInt()))
                .setKeyConfiguration(
                    ClientUtils.collection(in, unused -> new CacheKeyConfiguration(reader.readString(), reader.readString()))
                        .toArray(new CacheKeyConfiguration[0])
                ).setQueryEntities(ClientUtils.collection(
                    in,
                    unused -> {
                        QueryEntity qryEntity = new QueryEntity(reader.readString(), reader.readString())
                            .setTableName(reader.readString())
                            .setKeyFieldName(reader.readString())
                            .setValueFieldName(reader.readString());

                        boolean isCliVer1_2 = ver.compareTo(V1_2_0) >= 0;

                        Collection<QueryField> qryFields = ClientUtils.collection(
                            in,
                            unused2 -> {
                                String name = reader.readString();
                                String typeName = reader.readString();
                                boolean isKey = reader.readBoolean();
                                boolean isNotNull = reader.readBoolean(); 
                                Object dfltVal = reader.readObject();
                                int precision = isCliVer1_2 ? reader.readInt() : -1;
                                int scale = isCliVer1_2 ? reader.readInt() : -1; 

                                return new QueryField(name,
                                    typeName,
                                    isKey,
                                    isNotNull,
                                    dfltVal,
                                    precision,
                                    scale);
                            }
                        );

                        return qryEntity
                            .setFields(qryFields.stream().collect(Collectors.toMap(
                                QueryField::getName, QueryField::getTypeName, (a, b) -> a, LinkedHashMap::new
                            )))
                            .setKeyFields(qryFields.stream()
                                .filter(QueryField::isKey)
                                .map(QueryField::getName)
                                .collect(Collectors.toCollection(LinkedHashSet::new))
                            )
                            .setNotNullFields(qryFields.stream()
                                .filter(QueryField::isNotNull)
                                .map(QueryField::getName)
                                .collect(Collectors.toSet())
                            )
                            .setDefaultFieldValues(qryFields.stream()
                                .filter(f -> f.getDefaultValue() != null)
                                .collect(Collectors.toMap(QueryField::getName, QueryField::getDefaultValue))
                            )
                            .setFieldsPrecision(qryFields.stream()
                                .filter(f -> f.getPrecision() != -1)
                                .collect(Collectors.toMap(QueryField::getName, QueryField::getPrecision))
                            )
                            .setFieldsScale(qryFields.stream()
                                .filter(f -> f.getScale() != -1)
                                .collect(Collectors.toMap(QueryField::getName, QueryField::getScale))
                            )
                            .setAliases(ClientUtils.collection(
                                in,
                                unused3 -> new SimpleEntry<>(reader.readString(), reader.readString())
                            ).stream().collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)))
                            .setIndexes(ClientUtils.collection(
                                in,
                                unused4 -> {
                                    String name = reader.readString();
                                    QueryIndexType type = QueryIndexType.fromOrdinal(reader.readByte());
                                    int inlineSize = reader.readInt();

                                    LinkedHashMap<String, Boolean> fields = ClientUtils.collection(
                                        in,
                                        unused5 -> new SimpleEntry<>(reader.readString(), reader.readBoolean())
                                    ).stream().collect(Collectors.toMap(
                                        SimpleEntry::getKey,
                                        SimpleEntry::getValue,
                                        (a, b) -> a,
                                        LinkedHashMap::new
                                    ));

                                    return new QueryIndex(fields, type).setName(name).setInlineSize(inlineSize);
                                }
                            ));
                    }
                ).toArray(new QueryEntity[0]));
        }
    }