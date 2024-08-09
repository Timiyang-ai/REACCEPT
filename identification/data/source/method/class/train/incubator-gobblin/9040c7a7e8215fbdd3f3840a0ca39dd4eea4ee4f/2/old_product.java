private void cleanUp() {
      //Clean up failed dags
      for (String dagId : this.failedDagIds) {
        //Skip monitoring of any other jobs of the failed dag.
        LinkedList<Dag.DagNode<JobExecutionPlan>> dagNodeList = this.dagToJobs.get(dagId);
        while (!dagNodeList.isEmpty()) {
          Dag.DagNode<JobExecutionPlan> dagNode = dagNodeList.poll();
          deleteJobState(dagId, dagNode);
        }
        log.info("Dag {} has finished with status FAILED; Cleaning up dag from the state store.", dagId);
        cleanUpDag(dagId);
      }

      //Clean up successfully completed dags
      for (String dagId : this.dags.keySet()) {
        if (!hasRunningJobs(dagId)) {
          log.info("Dag {} has finished with status COMPLETE; Cleaning up dag from the state store.", dagId);
          cleanUpDag(dagId);
        }
      }
    }