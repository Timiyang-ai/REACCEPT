@Test
    public void testConcat() throws Exception {
        final String context = "Concat";
        try (Storage s = createStorage()) {
            HashMap<String, ByteArrayOutputStream> appendData = populate(s, context);

            // Check invalid segment name.
            val firstSegmentName = getSegmentName(0, context);
            s.open(firstSegmentName).join();
            AtomicLong firstSegmentLength = new AtomicLong(s.getStreamSegmentInfo(firstSegmentName,
                    TIMEOUT).join().getLength());
            assertThrows("concat() did not throw for non-existent target segment name.",
                    () -> s.concat("foo1", 0, firstSegmentName, TIMEOUT),
                    ex -> ex instanceof StreamSegmentNotExistsException);

            assertThrows("concat() did not throw for invalid source StreamSegment name.",
                    () -> s.concat(firstSegmentName, firstSegmentLength.get(), "foo2", TIMEOUT),
                    ex -> ex instanceof StreamSegmentNotExistsException);

            ArrayList<String> concatOrder = new ArrayList<>();
            concatOrder.add(firstSegmentName);
            for (String sourceSegment : appendData.keySet()) {
                if (sourceSegment.equals(firstSegmentName)) {
                    // FirstSegment is where we'll be concatenating to.
                    continue;
                }

                s.open(sourceSegment).join();
                assertThrows("Concat allowed when source segment is not sealed.",
                        () -> s.concat(firstSegmentName, firstSegmentLength.get(), sourceSegment, TIMEOUT),
                        ex -> ex instanceof IllegalStateException);

                // Seal the source segment and then re-try the concat
                s.seal(sourceSegment, TIMEOUT).join();
                SegmentProperties preConcatTargetProps = s.getStreamSegmentInfo(firstSegmentName, TIMEOUT).join();
                SegmentProperties sourceProps = s.getStreamSegmentInfo(sourceSegment, TIMEOUT).join();

                s.concat(firstSegmentName, firstSegmentLength.get(), sourceSegment, TIMEOUT).join();
                concatOrder.add(sourceSegment);
                SegmentProperties postConcatTargetProps = s.getStreamSegmentInfo(firstSegmentName, TIMEOUT).join();
                Assert.assertFalse("concat() did not delete source segment", s.exists(sourceSegment, TIMEOUT).join());

                // Only check lengths here; we'll check the contents at the end.
                Assert.assertEquals("Unexpected target StreamSegment.length after concatenation.",
                        preConcatTargetProps.getLength() + sourceProps.getLength(), postConcatTargetProps.getLength());
                firstSegmentLength.set(postConcatTargetProps.getLength());
            }

            // Check the contents of the first StreamSegment. We already validated that the length is correct.
            SegmentProperties segmentProperties = s.getStreamSegmentInfo(firstSegmentName, TIMEOUT).join();
            byte[] readBuffer = new byte[(int) segmentProperties.getLength()];

            // Read the entire StreamSegment.
            int bytesRead = s.read(firstSegmentName, 0, readBuffer, 0, readBuffer.length, TIMEOUT).join();
            Assert.assertEquals("Unexpected number of bytes read.", readBuffer.length, bytesRead);

            // Check, concat-by-concat, that the final data is correct.
            int offset = 0;
            for (String segmentName : concatOrder) {
                byte[] concatData = appendData.get(segmentName).toByteArray();
                AssertExtensions.assertArrayEquals("Unexpected concat data.", concatData, 0, readBuffer, offset,
                        concatData.length);
                offset += concatData.length;
            }

            Assert.assertEquals("Concat included more bytes than expected.", offset, readBuffer.length);
        }
    }