	@Test
	public void childPath() {
		TREE.forEach(node -> {
			final Path path = node.childPath();
			Assert.assertEquals(
				TREE.childAtPath(path).orElseThrow(AssertionError::new),
				node
			);
		});
	}