public List<TaskVO> queryTodoList(String user) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<TaskVO> todos = new ArrayList<TaskVO>();	
		
		List<TaskSummary> tasks = null;
		if(!"".equals(userGroupWsUrl) && !"".equals(userGroupMethod)){
			List<String> groups = null;
			try {
				groups = WSUtil.invokeUserGroup(user,userGroupWsUrl,userGroupMethod);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tasks = getJbpmSupport().findTaskSummaryByGroup(user, groups);
		}else{
			tasks = getJbpmSupport().findTaskSummary(user);
		}
		for (TaskSummary task : tasks) {
			long processId = task.getProcessInstanceId();
			RuleFlowProcessInstance in = null;
			try {
				ProcessInstance instance = getJbpmSupport().getProcessInstance(
						processId);
				if (instance == null)
					continue;
				in = (RuleFlowProcessInstance) instance;
			} catch (Exception e) {
				continue;
			}
			ProcessInstanceLog log = jbpmTaskService
					.findProcessInstance(processId);
			TaskVO todo = new TaskVO();
			Map<String, Object> processParams = in.getVariables();
			String processData = XmlParseUtil.paramsToXml(processParams);
			todo.setActualOwner(task.getActualOwner().getId());
			todo.setActualName(task.getName());
			todo.setProcessInstanceId(processId);
			todo.setProcessId(in.getProcessId());
			try {
				todo.setProcessName(in.getProcessName());
			} catch (Exception e) {
				todo.setProcessName(in.getProcessId());
			}
			todo.setTaskId(task.getId());
			todo.setProcessData(processData);
			//todo.setCreateDate(df.format(log.getStart()));
			if(in.getVariable("_process_creater")!=null){
				todo.setCreater((String)in.getVariable("_process_creater"));
			}
			todos.add(todo);
		}
		
		// 以下是查询委托待办
		List<KoalaAssignInfo> koalaAssignInfoList = jbpmTaskService
				.queryKoalaAssignInfo(user, new Date());
		if (koalaAssignInfoList != null && !koalaAssignInfoList.isEmpty()) {
			Set<String> agetnUserList = new HashSet<String>();
			//委托待办任务
			List<TaskSummary> agentTasks = new ArrayList<TaskSummary>();
			for (KoalaAssignInfo koalaAssignInfo : koalaAssignInfoList) {
				agetnUserList.add(koalaAssignInfo.getAssigner());
				//如果koalaAssignInfo中指定的流程为空，则表明所有流程都委托，否指定某个流程进行待办
				if(koalaAssignInfo.getJbpmNames()==null || koalaAssignInfo.getJbpmNames().size()==0){
					
					tasks = null;
					if(!"".equals(userGroupWsUrl) && !"".equals(userGroupMethod)){
						List<String> groups = null;
						try {
							groups = WSUtil.invokeUserGroup(user,userGroupWsUrl,userGroupMethod);
						} catch (Exception e) {
							e.printStackTrace();
						}
						tasks = getJbpmSupport().findTaskSummaryByGroup(user, groups);
					}else{
						tasks = getJbpmSupport().findTaskSummary(user);
					}
					
					agentTasks.addAll(tasks);
				}else{
					//如果指定了迁移的流程，则只委托指定的流程
					List<TaskSummary> assignTasks = null;
					if(!"".equals(userGroupWsUrl) && !"".equals(userGroupMethod)){
						List<String> groups = null;
						try {
							groups = WSUtil.invokeUserGroup(user,userGroupWsUrl,userGroupMethod);
						} catch (Exception e) {
							e.printStackTrace();
						}
						assignTasks = getJbpmSupport().findTaskSummaryByGroup(user, groups);
					}else{
						assignTasks = getJbpmSupport().findTaskSummary(user);
					}
					
					List<String> allowProcess = new ArrayList<String>();
					for(KoalaAssignDetail detail:koalaAssignInfo.getJbpmNames()){
						allowProcess.add(detail.getProcessId());
					}
					for(TaskSummary task:assignTasks){
						String processId = task.getProcessId();
						if(processId.contains("@"))processId = processId.substring(0,processId.indexOf("@"));
						if(allowProcess.contains(processId)){
							agentTasks.add(task);
						}
					}
				}
			}

			for (TaskSummary task : agentTasks) {
				long processId = task.getProcessInstanceId();
				RuleFlowProcessInstance in = null;
				try {
					ProcessInstance instance = getJbpmSupport()
							.getProcessInstance(processId);
					if (instance == null)
						continue;
					in = (RuleFlowProcessInstance) instance;
					// WorkItem workItem = ((WorkItemManager)
					// in.getKnowledgeRuntime().getWorkItemManager()).getWorkItem(contentId);
					// Object obj = in.getNodeInstances(workItem.getId());
					// System.out.println(workItem.getParameters());
				} catch (Exception e) {
					continue;
				}
				ProcessInstanceLog log = jbpmTaskService.findProcessInstance(
						processId);
				TaskVO todo = new TaskVO();
				Map<String, Object> processParams = in.getVariables();
				String processData = XmlParseUtil.paramsToXml(processParams);
				todo.setActualOwner(task.getActualOwner().getId());
				todo.setActualName(task.getName());
				todo.setProcessInstanceId(processId);
				todo.setProcessId(in.getProcessId());
				try {
					todo.setProcessName(in.getProcessName());
				} catch (Exception e) {
					todo.setProcessName(in.getProcessId());
				}
				todo.setTaskId(task.getId());
				todo.setProcessData(processData);
				todo.setCreateDate(df.format(log.getStart()));
				todo.setAgents("是");
				// todo.setCreater((String)in.getVariable("_process_creater"));
				todos.add(todo);
			}
		}
		return todos;
	}