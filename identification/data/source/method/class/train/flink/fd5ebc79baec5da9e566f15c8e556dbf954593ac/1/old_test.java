@Test
	public void testTimeOutRecords() {
		faultTolerancyBuffer.setTIMEOUT(1000);

		StreamRecord record1 = (new StreamRecord(1)).setId("1");
		record1.addRecord(new StringValue("V1"));
		StreamRecord record2 = (new StreamRecord(1)).setId("1");
		record2.addRecord(new StringValue("V2"));
		StreamRecord record3 = (new StreamRecord(1)).setId("1");
		record3.addRecord(new StringValue("V3"));

		faultTolerancyBuffer.addRecord(record1);
		faultTolerancyBuffer.addRecord(record2);

		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
		faultTolerancyBuffer.addRecord(record3);

		Long record1TS = faultTolerancyBuffer.getRecordTimestamps().get(
				record1.getId());
		Long record2TS = faultTolerancyBuffer.getRecordTimestamps().get(
				record2.getId());

		faultTolerancyBuffer.ackRecord(record1.getId());
		faultTolerancyBuffer.ackRecord(record1.getId());
		faultTolerancyBuffer.ackRecord(record1.getId());

		faultTolerancyBuffer.ackRecord(record2.getId());

		faultTolerancyBuffer.ackRecord(record3.getId());
		faultTolerancyBuffer.ackRecord(record3.getId());

		try {
			Thread.sleep(501);
		} catch (InterruptedException e) {
		}

		List<String> timedOutRecords = faultTolerancyBuffer.timeoutRecords(System
				.currentTimeMillis());

		System.out.println("timedOutRecords: " + timedOutRecords);

		assertEquals(1, timedOutRecords.size());
		assertFalse(timedOutRecords.contains(record1.getId()));
		assertFalse(faultTolerancyBuffer.getRecordsByTime().containsKey(record1TS));
		assertFalse(faultTolerancyBuffer.getRecordsByTime().containsKey(record2TS));

		assertTrue(faultTolerancyBuffer.getRecordBuffer().containsKey(
				record2.getId()));
		assertTrue(faultTolerancyBuffer.getAckCounter()
				.containsKey(record2.getId()));
		assertTrue(faultTolerancyBuffer.getRecordTimestamps().containsKey(
				record2.getId()));

		System.out.println(faultTolerancyBuffer.getAckCounter());

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}

		timedOutRecords = faultTolerancyBuffer.timeoutRecords(System
				.currentTimeMillis());
		assertEquals(null, timedOutRecords);

		try {
			Thread.sleep(901);
		} catch (InterruptedException e) {
		}

		timedOutRecords = faultTolerancyBuffer.timeoutRecords(System
				.currentTimeMillis());
		System.out.println("timedOutRecords: " + timedOutRecords);

		assertEquals(2, timedOutRecords.size());

		System.out.println(faultTolerancyBuffer.getAckCounter());
		System.out.println("---------");
	}