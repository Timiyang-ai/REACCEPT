@Test
    public void testConcat() throws Exception {
        try (Storage s = createStorage()) {
            HashMap<String, ByteArrayOutputStream> appendData = populate(s);

            // Check pre-create concat.
            String firstSegmentName = getSegmentName(0);
            long firstSegmentLength = s.getStreamSegmentInfo(firstSegmentName, TIMEOUT).join().getLength();
            AssertExtensions.assertThrows(
                    "concat() did not throw for non-existent target StreamSegment.",
                    s.concat("foo1", 0, firstSegmentName, TIMEOUT),
                    ex -> ex instanceof StreamSegmentNotExistsException);

            AssertExtensions.assertThrows(
                    "concat() did not throw for non-existent source StreamSegment.",
                    s.concat(firstSegmentName, firstSegmentLength, "foo2", TIMEOUT),
                    ex -> ex instanceof StreamSegmentNotExistsException);

            ArrayList<String> concatOrder = new ArrayList<>();
            concatOrder.add(firstSegmentName);
            for (String segmentName : appendData.keySet()) {
                if (segmentName.equals(firstSegmentName)) {
                    // FirstSegment is where we'll be concatenating to.
                    continue;
                }

                AssertExtensions.assertThrows(
                        "Concat allowed when source segment is not sealed.",
                        () -> s.concat(firstSegmentName, firstSegmentLength, segmentName, TIMEOUT),
                        ex -> ex instanceof IllegalStateException);

                // Seal the source segment and then re-try the concat
                s.seal(segmentName, TIMEOUT).join();
                SegmentProperties preConcatTargetProps = s.getStreamSegmentInfo(firstSegmentName, TIMEOUT).join();
                SegmentProperties sourceProps = s.getStreamSegmentInfo(segmentName, TIMEOUT).join();

                s.concat(firstSegmentName, firstSegmentLength, segmentName, TIMEOUT).join();
                concatOrder.add(segmentName);
                SegmentProperties postConcatTargetProps = s.getStreamSegmentInfo(firstSegmentName, TIMEOUT).join();
                AssertExtensions.assertThrows(
                        "concat() did not delete source segment",
                        s.getStreamSegmentInfo(segmentName, TIMEOUT),
                        ex -> ex instanceof StreamSegmentNotExistsException);

                // Only check lengths here; we'll check the contents at the end.
                Assert.assertEquals("Unexpected target StreamSegment.length after concatenation.", preConcatTargetProps.getLength() + sourceProps.getLength(), postConcatTargetProps.getLength());
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
                AssertExtensions.assertArrayEquals("Unexpected concat data.", concatData, 0, readBuffer, offset, concatData.length);
                offset += concatData.length;
            }

            Assert.assertEquals("Concat included more bytes than expected.", offset, readBuffer.length);
        }
    }