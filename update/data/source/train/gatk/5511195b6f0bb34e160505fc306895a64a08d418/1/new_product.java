public static DoFn<ContextShard,ContextShard> subdivideAndFillReads(String bam, int outputShardSize, int margin, final ReadFilter optFilter) throws IOException {
        return new DoFnWLog<ContextShard, ContextShard>("subdivideAndFillReads") {
            private static final long serialVersionUID = 1L;
            private SamReader reader = null;
            private Storage.Objects storageClient = null;

            @Override
            public void startBundle(Context c) throws Exception {
                super.startBundle(c);
                if (BucketUtils.isCloudStorageUrl(bam)) {
                    storageClient = DataflowCommandLineProgram.HellbenderDataflowOptions.Methods.createStorageClient(c.getPipelineOptions().as(DataflowCommandLineProgram.HellbenderDataflowOptions.class));
                    reader = BAMIO.openBAM(storageClient, bam, ValidationStringency.SILENT);
                } else if (BucketUtils.isHadoopUrl(bam)) {
                    throw new RuntimeException("Sorry, Hadoop paths aren't yet supported");
                } else {
                    // read from local file (this only makes sense for the direct runner)
                    reader = SamReaderFactory.make().validationStringency(ValidationStringency.SILENT).open(new File(bam));
                }
            }

            @Override
            public void processElement(ProcessContext c) throws Exception {
                int maxReadsPerShard = 10_000;
                ContextShard shard = c.element();
                ArrayList<SimpleInterval> ints =new ArrayList<>();
                ints.add(shard.interval);
                List<SimpleInterval> subshards = IntervalUtils.cutToShards(ints, outputShardSize);
                int currentSubShardIndex = 0;
                final int lastValidPos = shard.interval.getEnd() + margin;
                final int firstValidPos = shard.interval.getStart() - margin;
                SimpleInterval currentSubShard = subshards.get(currentSubShardIndex);

                try (SAMRecordIterator query = reader.queryOverlapping(shard.interval.getContig(), shard.interval.getStart(), shard.interval.getEnd())) {
                    ArrayList<GATKRead> readsSoFar = new ArrayList<>();

                    while (query.hasNext()) {
                        SAMRecord r = query.next();
                        SAMRecordToGATKReadAdapter g = new SAMRecordToGATKReadAdapter(r);
                        // yes, it'd be a tad faster to check before the wrapping.
                        // But this keeps the code a tad simpler.
                        if (!accept(g, shard.interval)) {
                            continue;
                        }
                        if (null!=optFilter && !optFilter.test(g)) {
                            continue;
                        }
                        if (!g.isUnmapped()) {
                            // error out if we accept a read that sticks out too far
                            // (margin was too tight, the shard may end up missing relevant variants)
                            if (r.getAlignmentEnd()>lastValidPos) {
                                throw new GATKException("Margin was too tight, a read sticks out by "+(r.getAlignmentEnd()-shard.interval.getEnd())+", going all the way to "+r.getAlignmentEnd());
                            }
                            if (r.getAlignmentStart()<firstValidPos) {
                                throw new GATKException("Margin was too tight, a read starts early by "+(shard.interval.getStart()-r.getAlignmentStart())+", starting at "+r.getAlignmentStart());
                            }
                        }

                        while (currentSubShard.getEnd() < r.getStart()) {
                            if (!readsSoFar.isEmpty()) {
                                // ship this one.
                                ContextShard ret = shard.split(currentSubShard).withReads(readsSoFar);
                                c.output(ret);
                                readsSoFar = new ArrayList<>();
                            }
                            // move to the next shard
                            currentSubShard = subshards.get(++currentSubShardIndex);
                        }
                        int currentSubShardEnd;
                        if (g.isUnmapped()) {
                            if (!g.mateIsUnmapped()) {
                                currentSubShardEnd = g.getMateStart() + margin;
                            } else {
                                throw new GATKException.ShouldNeverReachHereException("How did an unmapped read make it to here? "+g.toString());
                            }
                        } else {
                            currentSubShardEnd = g.getStart() + margin;
                        }
                        // the header slows serialization too much
                        g.setHeader(null);
                        readsSoFar.add(g);
                        if (readsSoFar.size()>=maxReadsPerShard) {
                            log.info("Too many reads in this shard, splitting it."+readsSoFar.size());
                            // ship this one.
                            SimpleInterval thisInterval = new SimpleInterval(currentSubShard.getContig(), currentSubShard.getStart(), currentSubShardEnd);
                            // we grow the interval by "margin" to make sure we get all the variants we need.
                            // Since we already assume that margin has that property, we're good to go.
                            ContextShard ret = shard.split(thisInterval).withReads(readsSoFar);
                            c.output(ret);
                            readsSoFar = new ArrayList<>();
                            // do not advance currentSubShard, we're still technically in the same one.
                        }

                    }
                    // done reading, ship what we have
                    if (!readsSoFar.isEmpty()) {
                        ContextShard ret = shard.split(currentSubShard).withReads(readsSoFar);
                        c.output(ret);
                    }
                }
            }

            @Override
            public void finishBundle(Context c) throws Exception {
                if (null!=reader) {
                    reader.close();
                }
                super.finishBundle(c);
            }

            private boolean accept(GATKRead r, SimpleInterval region) {
                if (r.isUnmapped()) {
                    return (!r.mateIsUnmapped() && r.getMateStart() >= region.getStart());
                } else {
                    // mapped read.
                    // the query gives us reads that overlap the region. We want only reads that *start* in those intervals.
                    return r.getStart() >= region.getStart();
                }
            }

        };
    }