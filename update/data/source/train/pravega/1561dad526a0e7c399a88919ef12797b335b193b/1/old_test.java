@Test
    public void testGetContainer() throws Exception {
        final int containerCount = 1000;
        @Cleanup
        CloseableExecutorService executor = new CloseableExecutorService(Executors.newScheduledThreadPool(THREAD_POOL_SIZE));
        TestContainerFactory factory = new TestContainerFactory();
        StreamSegmentContainerRegistry registry = new StreamSegmentContainerRegistry(factory, executor.get());

        HashSet<String> expectedContainerIds = new HashSet<>();
        Collection<CompletableFuture<ContainerHandle>> handleFutures = new ArrayList<>();
        for (int i = 0; i < containerCount; i++) {
            String containerId = getContainerId(i);
            handleFutures.add(registry.startContainer(containerId, TIMEOUT));
            expectedContainerIds.add(containerId);
        }

        Collection<ContainerHandle> handles = FutureHelpers.allOfWithResults(handleFutures).join();
        HashSet<String> actualHandleIds = new HashSet<>();
        for (ContainerHandle handle : handles) {
            actualHandleIds.add(handle.getContainerId());
            SegmentContainer container = registry.getContainer(handle.getContainerId());
            Assert.assertTrue("Wrong container Java type.", container instanceof TestContainer);
            Assert.assertEquals("Unexpected container Id.", handle.getContainerId(), container.getId());
            container.close();
        }

        AssertExtensions.assertContainsSameElements("Unexpected container ids registered.", expectedContainerIds, actualHandleIds);

        AssertExtensions.assertThrows(
                "getContainer did not throw when passed an invalid container id.",
                () -> registry.getContainer("foo"),
                ex -> ex instanceof ContainerNotFoundException);
    }