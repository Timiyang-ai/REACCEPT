@Test(groups = {"cloud"})
    public void testSubdivideAndFillReads() throws IOException {
        String bam = "gs://hellbender/test/resources/org/broadinstitute/hellbender/tools/BQSR/NA12878.chr17_69k_70k.dictFix.bam";
        int outputShardSize = 50;
        int margin = 1000;
        ContextShard shard = new ContextShard(new SimpleInterval("17",69000,69100));
        List<ContextShard> shards = new ArrayList<>();
        shards.add(shard);

        // 809R9ABXX101220:5:46:2178:137187 at 69032
        // 809R9ABXX101220:5:61:7847:167387 at 69038
        // then nothing until 69107
        HashSet<String> expectedReads = new HashSet<>();
        expectedReads.add("809R9ABXX101220:5:46:2178:137187");
        expectedReads.add("809R9ABXX101220:5:61:7847:167387");


        Pipeline p = GATKTestPipeline.create();
        p.getOptions().as(GCSOptions.class).setApiKey(System.getenv("HELLBENDER_TEST_APIKEY"));
        DataflowUtils.registerGATKCoders(p);
        PCollection<ContextShard> pShards = p.apply(Create.of(shards));
        pShards .apply(ParDo.of(AddContextDataToReadOptimized.subdivideAndFillReads(bam, outputShardSize, margin, null)))
                .apply(ParDo.of(new DoFn<ContextShard, String>() {
                    private static final long serialVersionUID = 1L;
                    @Override
                    public void processElement(ProcessContext c) throws Exception {
                        for (GATKRead r : c.element().reads) {
                            String n = r.getName();
                            System.out.println("Found "+n);
                            assertTrue(expectedReads.contains(n));
                            expectedReads.remove(n);
                            c.output(n);
                        }
                    }
                }))
                // we found all the reads we wanted
                .apply(Count.globally())
                .apply(ParDo.of(new DoFn<Long, Void>() {
                    private static final long serialVersionUID = 1L;
                                    @Override
                                    public void processElement(ProcessContext c) throws Exception {
                                        assertEquals(c.element().longValue(), 2L);
                                    }
                                }));
        p.run();
    }