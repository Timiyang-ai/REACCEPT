public static void generatePolicyDescription(PolicyBean policy) throws Exception {
        PolicyDefinitionBean def = policy.getDefinition();
        PolicyDefinitionTemplateBean templateBean = getTemplateBean(def);
        if (templateBean == null) {
            return;
        }
        String cacheKey = def.getId() + "::" + templateBean.getLanguage(); //$NON-NLS-1$
        CompiledTemplate template = templateCache.get(cacheKey);
        if (template == null) {
            template = TemplateCompiler.compileTemplate(templateBean.getTemplate());
            templateCache.put(cacheKey, template);
        }
        try {
            String jsonConfig = policy.getConfiguration();
            @SuppressWarnings("unchecked")
            Map<String, Object> configMap = mapper.readValue(jsonConfig, Map.class);
            String desc = (String) TemplateRuntime.execute(template, configMap);
            policy.setDescription(desc);
        } catch (Exception e) {
            // TODO log the error
            policy.setDescription(templateBean.getTemplate());
        }
    }