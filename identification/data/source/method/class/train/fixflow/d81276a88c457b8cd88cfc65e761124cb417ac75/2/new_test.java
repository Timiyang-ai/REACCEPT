	public void testGetFlowGraphicsImgPath(){
		DeploymentBuilder deploymentBuilder = modelService.createDeployment().name("测试名称");
		//添加你要发布的定义
		deploymentBuilder.addClasspathResource("com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn");
		//发布
		String deploymentIdTemp = deploymentBuilder.deploy().getId();
		//验证是否发布成功
		assertNotNull(deploymentIdTemp);
		//创建一个流程定义查询
		ProcessDefinitionQuery processDefinitionQuery=modelService.createProcessDefinitionQuery();
		//查询刚发布的流程定义
		processDefinitionQuery.processDefinitionKey("Process_TaskServiceTest");
		ProcessDefinitionBehavior processDefinitionBehavior=processDefinitionQuery.singleResult();
		//验证是否得到
		assertNotNull(processDefinitionBehavior);
		
		//根据流程定义唯一编号获取流程图img
		String imgPath=modelService.GetFlowGraphicsImgPath(processDefinitionBehavior.getProcessDefinitionId());
		//验证是否获得img的path
		assertNotNull(imgPath);
	}