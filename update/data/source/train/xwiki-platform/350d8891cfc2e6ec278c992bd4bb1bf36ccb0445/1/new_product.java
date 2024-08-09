public void getMacroDescriptor(final String macroId, final String syntaxId,
        final AsyncCallback<MacroDescriptor> async)
    {
        getMacroDescriptor(macroId, syntaxId, null, async);
    }