@Test
    public void testConcat() throws Exception {
        final String context = "Concat";
        try (Storage s = createStorage()) {
            HashMap<String, ByteArrayOutputStream> appendData = populate(s, context);

            // Check invalid handle.
            val firstSegmentHandle = s.open(getSegmentName(0, context), TIMEOUT).join();
            AtomicLong firstSegmentLength = new AtomicLong(s.getStreamSegmentInfo(firstSegmentHandle, TIMEOUT).join().getLength());
            assertThrows("concat() did not throw invalid target StreamSegment handle.",
                    () -> s.concat(createInvalidHandle("foo1"), 0, firstSegmentHandle, TIMEOUT),
                    ex -> ex instanceof InvalidSegmentHandleException);

            assertThrows("concat() did not throw for invalid source StreamSegment handle.",
                    () -> s.concat(firstSegmentHandle, firstSegmentLength.get(), createInvalidHandle("foo2"), TIMEOUT),
                    ex -> ex instanceof InvalidSegmentHandleException);

            ArrayList<String> concatOrder = new ArrayList<>();
            concatOrder.add(firstSegmentHandle.getSegmentName());
            for (String sourceSegment : appendData.keySet()) {
                if (sourceSegment.equals(firstSegmentHandle.getSegmentName())) {
                    // FirstSegment is where we'll be concatenating to.
                    continue;
                }

                val sourceHandle = s.open(sourceSegment, TIMEOUT).join();
                assertThrows("Concat allowed when source segment is not sealed.",
                        () -> s.concat(firstSegmentHandle, firstSegmentLength.get(), sourceHandle, TIMEOUT),
                        ex -> ex instanceof IllegalStateException);

                // Seal the source segment and then re-try the concat
                s.seal(sourceHandle, TIMEOUT).join();
                SegmentProperties preConcatTargetProps = s.getStreamSegmentInfo(firstSegmentHandle, TIMEOUT).join();
                SegmentProperties sourceProps = s.getStreamSegmentInfo(sourceHandle, TIMEOUT).join();

                s.concat(firstSegmentHandle, firstSegmentLength.get(), sourceHandle, TIMEOUT).join();
                concatOrder.add(sourceSegment);
                SegmentProperties postConcatTargetProps = s.getStreamSegmentInfo(firstSegmentHandle, TIMEOUT).join();
                Assert.assertFalse("concat() did not delete source segment", s.exists(sourceHandle, TIMEOUT).join());

                // Only check lengths here; we'll check the contents at the end.
                Assert.assertEquals("Unexpected target StreamSegment.length after concatenation.", preConcatTargetProps.getLength() + sourceProps.getLength(), postConcatTargetProps.getLength());
                firstSegmentLength.set(postConcatTargetProps.getLength());
            }

            // Check the contents of the first StreamSegment. We already validated that the length is correct.
            SegmentProperties segmentProperties = s.getStreamSegmentInfo(firstSegmentHandle, TIMEOUT).join();
            byte[] readBuffer = new byte[(int) segmentProperties.getLength()];

            // Read the entire StreamSegment.
            int bytesRead = s.read(firstSegmentHandle, 0, readBuffer, 0, readBuffer.length, TIMEOUT).join();
            Assert.assertEquals("Unexpected number of bytes read.", readBuffer.length, bytesRead);

            // Check, concat-by-concat, that the final data is correct.
            int offset = 0;
            for (String segmentName : concatOrder) {
                byte[] concatData = appendData.get(segmentName).toByteArray();
                AssertExtensions.assertArrayEquals("Unexpected concat data.", concatData, 0, readBuffer, offset, concatData.length);
                offset += concatData.length;
            }

            Assert.assertEquals("Concat included more bytes than expected.", offset, readBuffer.length);
        }
    }