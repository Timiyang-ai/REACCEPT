@Test
	public void testIsSavepoint() throws Exception {
		{
			CheckpointProperties props = CheckpointProperties.forStandardCheckpoint();
			assertFalse(props.isSavepoint());
		}

		{
			CheckpointProperties props = CheckpointProperties.forExternalizedCheckpoint(true);
			assertFalse(props.isSavepoint());
		}

		{
			CheckpointProperties props = CheckpointProperties.forExternalizedCheckpoint(false);
			assertFalse(props.isSavepoint());
		}

		{
			CheckpointProperties props = CheckpointProperties.forStandardSavepoint();
			assertTrue(props.isSavepoint());

			CheckpointProperties deserializedCheckpointProperties =
				InstantiationUtil.deserializeObject(
					InstantiationUtil.serializeObject(props),
					getClass().getClassLoader());
			assertTrue(deserializedCheckpointProperties.isSavepoint());
		}

	}