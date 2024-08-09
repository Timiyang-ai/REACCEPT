public void roolBack(long processInstanceId, long taskId, String userId) {
		try {
			this.getJbpmSupport().startTransaction();
			// RuleFlowProcessInstance in = (RuleFlowProcessInstance)
			// getJbpmSupport()
			// .getProcessInstance(processInstanceId);
			// List<String> actives = in.getActiveNodeIds();
			// if (actives.size() > 1) {
			// throw new RuntimeException("多个任务情况下不支持回退");
			// }
			HistoryLog historyLog = HistoryLog
					.queryLastActivedNodeId(processInstanceId);
			if (historyLog == null || historyLog.getNodeId() == 0) {
				throw new RuntimeException("没有上一个人工处理节点，不能回退");
			}
			assignToNodeCall(processInstanceId, historyLog.getNodeId());
			this.getJbpmSupport().commitTransaction();
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.getJbpmSupport().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			this.getJbpmSupport().rollbackTransaction();
			e.printStackTrace();
			throw new RuntimeException(e.getCause());
		}
	}