public void assignToNode(long processInstanceId, long nodeId) {
		UserTransaction owner = null;
		try {
			owner = startUserTransaction();
			this.assignToNodeCall(processInstanceId, nodeId);
			this.commitUserTransaction(owner);
		} catch (RuntimeException e) {
			e.printStackTrace();
			rollbackUserTransaction(owner);
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			this.rollbackUserTransaction(owner);
			throw new RuntimeException(e.getCause());
		}
	}