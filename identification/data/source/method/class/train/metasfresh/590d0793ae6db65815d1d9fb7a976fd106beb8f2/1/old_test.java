	@Test
	public void isBefore()
	{
		final DateAndSeqNo dateAndSeqNo1 = DateAndSeqNo.atTimeNoSeqNo(t1);
		final DateAndSeqNo dateAndSeqNo2 = DateAndSeqNo.atTimeNoSeqNo(t2);

		assertThat(dateAndSeqNo1.isBefore(dateAndSeqNo2)).isTrue();
		assertThat(dateAndSeqNo2.isBefore(dateAndSeqNo2)).isFalse();
		assertThat(dateAndSeqNo2.isBefore(dateAndSeqNo1)).isFalse();
	}