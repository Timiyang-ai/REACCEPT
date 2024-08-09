public List<Block> execute(WikiMacroParameters parameters, String macroContent, MacroTransformationContext context)
        throws MacroExecutionException
    {
        validate(parameters, macroContent);

        // Parse the wiki macro content.
        XDOM xdom = prepareWikiMacroContent(context);

        // Prepare macro context.
        Map<String, Object> macroBinding = new HashMap<String, Object>();
        macroBinding.put(MACRO_PARAMS_KEY, parameters);
        macroBinding.put(MACRO_CONTENT_KEY, macroContent);
        macroBinding.put(MACRO_CONTEXT_KEY, context);
        macroBinding.put(MACRO_RESULT_KEY, null);

        // Extension point to add more wiki macro bindings
        try {
            List<WikiMacroBindingInitializer> bindingInitializers =
                this.componentManager.lookupList(WikiMacroBindingInitializer.class);

            for (WikiMacroBindingInitializer bindingInitializer : bindingInitializers) {
                bindingInitializer.initialize(this.macroDocumentReference, parameters, macroContent, context,
                    macroBinding);
            }
        } catch (ComponentLookupException e) {
            // TODO: we should probably log something but that should never happen
        }

        // Execute the macro
        ObservationManager observation = null;
        try {
            observation = this.componentManager.lookup(ObservationManager.class);
        } catch (ComponentLookupException e) {
            // TODO: maybe log something
        }

        try {
            Transformation macroTransformation = this.componentManager.lookup(Transformation.class, MACRO_HINT);

            // Place macro context inside xwiki context ($context.macro).
            Execution execution = this.componentManager.lookup(Execution.class);
            Map<String, Object> xwikiContext = (Map<String, Object>) execution.getContext().getProperty("xwikicontext");
            xwikiContext.put(MACRO_KEY, macroBinding);

            MacroBlock wikiMacroBlock = context.getCurrentMacroBlock();
            MacroMarkerBlock wikiMacroMarker =
                new MacroMarkerBlock(wikiMacroBlock.getId(), wikiMacroBlock.getParameters(),
                    wikiMacroBlock.getContent(), xdom.getChildren(), wikiMacroBlock.isInline());
            // otherwise the inner macros will not be able to access the parent DOM
            wikiMacroMarker.setParent(wikiMacroBlock.getParent());

            if (observation != null) {
                observation.notify(STARTEXECUTION_EVENT, this, macroBinding);
            }

            // Perform internal macro transformations.
            TransformationContext txContext = new TransformationContext(context.getXDOM(), this.syntax);
            macroTransformation.transform(wikiMacroMarker, txContext);

            return extractResult(wikiMacroMarker.getChildren(), macroBinding, context);
        } catch (Exception ex) {
            throw new MacroExecutionException("Error while performing internal macro transformations", ex);
        } finally {
            if (observation != null) {
                observation.notify(ENDEXECUTION_EVENT, this);
            }
        }
    }