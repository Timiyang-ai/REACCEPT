@Test
    public void testGeneratePolicyDescription_missingvar() throws Exception {
        PolicyBean policy = new PolicyBean();
        PolicyDefinitionBean def = new PolicyDefinitionBean();
        def.setId("missingvar"); //$NON-NLS-1$
        PolicyDefinitionTemplateBean template = new PolicyDefinitionTemplateBean();
        template.setTemplate("Template includes a missing var: @{missingVar}"); //$NON-NLS-1$
        def.getTemplates().add(template);
        policy.setDefinition(def);
        policy.setConfiguration("{}"); //$NON-NLS-1$
        PolicyTemplateUtil.generatePolicyDescription(policy);
        Assert.assertEquals("Template includes a missing var: null", policy.getDescription()); //$NON-NLS-1$
    }