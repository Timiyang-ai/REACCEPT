private void cleanUp() {
      //Clean up failed dags
      for (String dagId : this.failedDagIdsFinishRunning) {
        //Skip monitoring of any other jobs of the failed dag.
        LinkedList<DagNode<JobExecutionPlan>> dagNodeList = this.dagToJobs.get(dagId);
        while (!dagNodeList.isEmpty()) {
          DagNode<JobExecutionPlan> dagNode = dagNodeList.poll();
          deleteJobState(dagId, dagNode);
        }
        log.info("Dag {} has finished with status FAILED; Cleaning up dag from the state store.", dagId);
        cleanUpDag(dagId);
      }

      //Clean up completed dags
      for (String dagId : this.dags.keySet()) {
        if (!hasRunningJobs(dagId)) {
          String status = "COMPLETE";
          if (this.failedDagIdsFinishAllPossible.contains(dagId)) {
            status = "FAILED";
            this.failedDagIdsFinishAllPossible.remove(dagId);
          }
          log.info("Dag {} has finished with status {}; Cleaning up dag from the state store.", dagId, status);
          cleanUpDag(dagId);
        }
      }
    }