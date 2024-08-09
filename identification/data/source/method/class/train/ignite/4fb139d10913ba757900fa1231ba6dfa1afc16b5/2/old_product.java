void cacheConfiguration(ClientCacheConfiguration cfg, BinaryOutputStream out, ProtocolVersion ver) {
        try (BinaryRawWriterEx writer = new BinaryWriterExImpl(marsh.context(), out, null, null)) {
            int origPos = out.position();

            writer.writeInt(0); // configuration length is to be assigned in the end
            writer.writeShort((short)0); // properties count is to be assigned in the end

            AtomicInteger propCnt = new AtomicInteger(0);

            BiConsumer<CfgItem, Consumer<BinaryRawWriter>> itemWriter = (cfgItem, cfgWriter) -> {
                writer.writeShort(cfgItem.code());

                cfgWriter.accept(writer);

                propCnt.incrementAndGet();
            };

            itemWriter.accept(CfgItem.NAME, w -> w.writeString(cfg.getName()));
            itemWriter.accept(CfgItem.CACHE_MODE, w -> w.writeInt(cfg.getCacheMode().ordinal()));
            itemWriter.accept(CfgItem.ATOMICITY_MODE, w -> w.writeInt(cfg.getAtomicityMode().ordinal()));
            itemWriter.accept(CfgItem.BACKUPS, w -> w.writeInt(cfg.getBackups()));
            itemWriter.accept(CfgItem.WRITE_SYNC_MODE, w -> w.writeInt(cfg.getWriteSynchronizationMode().ordinal()));
            itemWriter.accept(CfgItem.READ_FROM_BACKUP, w -> w.writeBoolean(cfg.isReadFromBackup()));
            itemWriter.accept(CfgItem.EAGER_TTL, w -> w.writeBoolean(cfg.isEagerTtl()));
            itemWriter.accept(CfgItem.GROUP_NAME, w -> w.writeString(cfg.getGroupName()));
            itemWriter.accept(CfgItem.DEFAULT_LOCK_TIMEOUT, w -> w.writeLong(cfg.getDefaultLockTimeout()));
            itemWriter.accept(CfgItem.PART_LOSS_POLICY, w -> w.writeInt(cfg.getPartitionLossPolicy().ordinal()));
            itemWriter.accept(CfgItem.REBALANCE_BATCH_SIZE, w -> w.writeInt(cfg.getRebalanceBatchSize()));
            itemWriter.accept(CfgItem.REBALANCE_BATCHES_PREFETCH_COUNT, w -> w.writeLong(cfg.getRebalanceBatchesPrefetchCount()));
            itemWriter.accept(CfgItem.REBALANCE_DELAY, w -> w.writeLong(cfg.getRebalanceDelay()));
            itemWriter.accept(CfgItem.REBALANCE_MODE, w -> w.writeInt(cfg.getRebalanceMode().ordinal()));
            itemWriter.accept(CfgItem.REBALANCE_ORDER, w -> w.writeInt(cfg.getRebalanceOrder()));
            itemWriter.accept(CfgItem.REBALANCE_THROTTLE, w -> w.writeLong(cfg.getRebalanceThrottle()));
            itemWriter.accept(CfgItem.REBALANCE_TIMEOUT, w -> w.writeLong(cfg.getRebalanceTimeout()));
            itemWriter.accept(CfgItem.COPY_ON_READ, w -> w.writeBoolean(cfg.isCopyOnRead()));
            itemWriter.accept(CfgItem.DATA_REGION_NAME, w -> w.writeString(cfg.getDataRegionName()));
            itemWriter.accept(CfgItem.STATS_ENABLED, w -> w.writeBoolean(cfg.isStatisticsEnabled()));
            itemWriter.accept(CfgItem.MAX_ASYNC_OPS, w -> w.writeInt(cfg.getMaxConcurrentAsyncOperations()));
            itemWriter.accept(CfgItem.MAX_QUERY_ITERATORS, w -> w.writeInt(cfg.getMaxQueryIteratorsCount()));
            itemWriter.accept(CfgItem.ONHEAP_CACHE_ENABLED, w -> w.writeBoolean(cfg.isOnheapCacheEnabled()));
            itemWriter.accept(CfgItem.QUERY_METRIC_SIZE, w -> w.writeInt(cfg.getQueryDetailMetricsSize()));
            itemWriter.accept(CfgItem.QUERY_PARALLELISM, w -> w.writeInt(cfg.getQueryParallelism()));
            itemWriter.accept(CfgItem.SQL_ESCAPE_ALL, w -> w.writeBoolean(cfg.isSqlEscapeAll()));
            itemWriter.accept(CfgItem.SQL_IDX_MAX_INLINE_SIZE, w -> w.writeInt(cfg.getSqlIndexMaxInlineSize()));
            itemWriter.accept(CfgItem.SQL_SCHEMA, w -> w.writeString(cfg.getSqlSchema()));
            itemWriter.accept(
                CfgItem.KEY_CONFIGS,
                w -> ClientUtils.collection(
                    cfg.getKeyConfiguration(),
                    out,
                    (unused, i) -> {
                        w.writeString(i.getTypeName());
                        w.writeString(i.getAffinityKeyFieldName());
                    }
                )
            );

            itemWriter.accept(
                CfgItem.QUERY_ENTITIES,
                w -> ClientUtils.collection(
                    cfg.getQueryEntities(),
                    out, (unused, e) -> {
                        w.writeString(e.getKeyType());
                        w.writeString(e.getValueType());
                        w.writeString(e.getTableName());
                        w.writeString(e.getKeyFieldName());
                        w.writeString(e.getValueFieldName());
                        ClientUtils.collection(
                            e.getFields().entrySet(),
                            out,
                            (unused2, f) -> {
                                QueryField qf = new QueryField(e, f);

                                w.writeString(qf.getName());
                                w.writeString(qf.getTypeName());
                                w.writeBoolean(qf.isKey());
                                w.writeBoolean(qf.isNotNull());
                                w.writeObject(qf.getDefaultValue());

                                if (ver.compareTo(V1_2_0) >= 0) {
                                    w.writeInt(qf.getPrecision());
                                    w.writeInt(qf.getScale());
                                }
                            }
                        );
                        ClientUtils.collection(
                            e.getAliases().entrySet(),
                            out, (unused3, a) -> {
                                w.writeString(a.getKey());
                                w.writeString(a.getValue());
                            }
                        );
                        ClientUtils.collection(
                            e.getIndexes(),
                            out,
                            (unused4, i) -> {
                                w.writeString(i.getName());
                                w.writeByte((byte)i.getIndexType().ordinal());
                                w.writeInt(i.getInlineSize());
                                ClientUtils.collection(i.getFields().entrySet(), out, (unused5, f) -> {
                                        w.writeString(f.getKey());
                                        w.writeBoolean(f.getValue());
                                    }
                                );
                            });
                    }
                )
            );

            writer.writeInt(origPos, out.position() - origPos - 4); // configuration length
            writer.writeInt(origPos + 4, propCnt.get()); // properties count
        }
    }