@Test
    public void testEPOC() throws Exception {

        File file = File.createTempFile("chronicle.", "q");
        file.deleteOnExit();
        try {

            final ChronicleQueue chronicle = new SingleChronicleQueueBuilder(getTmpDir())
                    .wireType(this.wireType)
                    .epoch(System.currentTimeMillis())
                    .rollCycle(RollCycles.HOURS)
                    .build();

            final ExcerptAppender appender = chronicle.createAppender();
            appender.writeDocument(wire -> wire.write(() -> "key").text("value=v"));
            Assert.assertTrue(appender.cycle() == 0);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }