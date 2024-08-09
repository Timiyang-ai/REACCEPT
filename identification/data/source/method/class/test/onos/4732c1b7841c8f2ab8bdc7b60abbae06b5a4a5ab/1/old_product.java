private void relinquish() {
        int activeNodes = (int) clusterService.getNodes()
                .stream()
                .filter(n -> clusterService.getState(n.id())
                        == ControllerNode.State.ACTIVE)
                .count();

        int myShare = (int) Math.ceil((double) NUM_PARTITIONS / activeNodes);

        List<Leadership> myPartitions = leadershipService.getLeaderBoard().values()
                .stream()
                .filter(l -> clusterService.getLocalNode().id().equals(l.leader()))
                .filter(l -> l.topic().startsWith(ELECTION_PREFIX))
                .collect(Collectors.toList());

        int relinquish = myPartitions.size() - myShare;

        if (relinquish <= 0) {
            return;
        }

        for (int i = 0; i < relinquish; i++) {
            String topic = myPartitions.get(i).topic();
            leadershipService.withdraw(topic);

            executor.schedule(() -> recontest(topic),
                              BACKOFF_TIME, TimeUnit.SECONDS);
        }
    }