    @Test
    public void hasCapacity() {
        assertTrue(capacity.hasCapacity(host1, resources1));
        assertTrue(capacity.hasCapacity(host1, resources2));
        assertTrue(capacity.hasCapacity(host2, resources1));
        assertTrue(capacity.hasCapacity(host2, resources2));
        assertFalse(capacity.hasCapacity(host3, resources1));  // No ip available
        assertFalse(capacity.hasCapacity(host3, resources2)); // No ip available

        // Add a new node to host1 to deplete the memory resource
        Node nodeF = Node.createDockerNode(Set.of("::6"), "nodeF", "host1", resources1, NodeType.tenant);
        nodes.add(nodeF);
        capacity = new DockerHostCapacity(new LockedNodeList(nodes, () -> {}), hostResourcesCalculator);
        assertFalse(capacity.hasCapacity(host1, resources1));
        assertFalse(capacity.hasCapacity(host1, resources2));
    }