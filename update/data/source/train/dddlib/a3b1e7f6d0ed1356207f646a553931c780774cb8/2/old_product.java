public void assignToNode(long processInstanceId, long nodeId) {
		RuleFlowProcessInstance in = (RuleFlowProcessInstance) getJbpmSupport()
				.getProcessInstance(processInstanceId);
		HumanTaskNode node = (HumanTaskNode) in.getNodeContainer().getNode(
				nodeId);
		List<WorkItemInfo> workItemInfos = jbpmTaskService
				.getWorkItemInfo(processInstanceId);
		HumanTaskNodeInstance human = (HumanTaskNodeInstance) in
				.getNodeInstance(node);
		// 如果流程当前节点与要激活的节点一样，则不转移
		boolean isSame = true;

		Collection<org.drools.runtime.process.NodeInstance> instances = in
				.getNodeInstances();
		for (org.drools.runtime.process.NodeInstance nodeInstance : instances) {
			if (nodeInstance.getNodeId() != human.getNodeId()) {
				isSame = false;
			}
		}
		in.setVariable("REMOVE", true);
		for (org.drools.runtime.process.NodeInstance nodeInstance : instances) {
			org.jbpm.workflow.instance.NodeInstance removeNode = in
					.getNodeInstance(nodeInstance.getId());
			if (removeNode != null) {
				in.removeNodeInstance(removeNode);
				in.removeEventListener("workItemCompleted",
						(HumanTaskNodeInstance) removeNode, false);
			}
		}
		jbpmTaskService.exitedTask(processInstanceId);
		human.internalTrigger(null, "");
		for (WorkItemInfo workItemInfo : workItemInfos) {
			jbpmTaskService.removeWorkItemInfo(workItemInfo);
		}
		if (isSame){
			return;
		}
			
		HistoryLog log = new HistoryLog();
		log.setComment("转移到节点:" + human.getNodeName());
		log.setCreateDate(new Date());
		log.setNodeName("人工转移");
		log.setResult("当前流程任务被管理员变更");
		log.setUser("Admin");
		log.setProcessInstanceId(processInstanceId);
		log.save();
	}