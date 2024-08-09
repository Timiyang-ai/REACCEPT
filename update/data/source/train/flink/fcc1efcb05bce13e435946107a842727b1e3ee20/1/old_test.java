@Test
	public void testIsSavepoint() throws Exception {
		{
			CheckpointProperties props = CheckpointProperties.forStandardCheckpoint();
			assertFalse(CheckpointProperties.isSavepoint(props));
		}

		{
			CheckpointProperties props = CheckpointProperties.forExternalizedCheckpoint(true);
			assertFalse(CheckpointProperties.isSavepoint(props));
		}

		{
			CheckpointProperties props = CheckpointProperties.forExternalizedCheckpoint(false);
			assertFalse(CheckpointProperties.isSavepoint(props));
		}

		{
			CheckpointProperties props = CheckpointProperties.forStandardSavepoint();
			assertTrue(CheckpointProperties.isSavepoint(props));
		}

	}