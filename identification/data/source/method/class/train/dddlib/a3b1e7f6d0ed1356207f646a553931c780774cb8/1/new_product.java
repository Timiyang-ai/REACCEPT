public void assignToNode(long processInstanceId, long nodeId) {
		try {
			this.getJbpmSupport().startTransaction();
			this.assignToNodeCall(processInstanceId, nodeId);
			this.getJbpmSupport().commitTransaction();
		} catch (RuntimeException e) {
			e.printStackTrace();
			getJbpmSupport().rollbackTransaction();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			this.getJbpmSupport().rollbackTransaction();
			throw new RuntimeException(e.getCause());
		}
	}