@Test
  public void testGetProcessDiagramLayout() throws Exception {
    DiagramLayout processDiagramLayout;
    if (1 == processDefinitionQuery.count()) {
      ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
      assertNotNull(processDefinition);
      processDiagramLayout = repositoryService.getProcessDiagramLayout(processDefinition.getId());
    } else {
      // some test diagrams do not contain executable processes
      // and are therefore ignored by the engine
      InputStream bpmnXmlStream = new FileInputStream("src/test/resources/org/activiti/engine/test/api/repository/diagram/" + xmlFileName);
      InputStream imageStream = new FileInputStream("src/test/resources/org/activiti/engine/test/api/repository/diagram/" + imageFileName);
      assertNotNull(bpmnXmlStream);
      assertNotNull(imageStream);
      processDiagramLayout = new ProcessDiagramLayoutFactory().getProcessDiagramLayout(bpmnXmlStream, imageStream);
    }
    assertLayoutCorrect(processDiagramLayout);
  }