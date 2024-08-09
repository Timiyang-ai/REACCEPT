public Object eval(final ScriptObject initialScope, final String string, final Object callThis, final Object location, final boolean strict) {
        final String  file       = (location == UNDEFINED || location == null) ? "<eval>" : location.toString();
        final Source  source     = new Source(file, string);
        final boolean directEval = location != UNDEFINED; // is this direct 'eval' call or indirectly invoked eval?
        final ScriptObject global = Context.getGlobalTrusted();

        ScriptObject scope = initialScope;

        // ECMA section 10.1.1 point 2 says eval code is strict if it begins
        // with "use strict" directive or eval direct call itself is made
        // from from strict mode code. We are passed with caller's strict mode.
        boolean strictFlag = directEval && strict;

        Class<?> clazz = null;
        try {
            clazz = compile(source, new ThrowErrorManager(), strictFlag);
        } catch (final ParserException e) {
            e.throwAsEcmaException(global);
            return null;
        }

        if (!strictFlag) {
            // We need to get strict mode flag from compiled class. This is
            // because eval code may start with "use strict" directive.
            try {
                strictFlag = clazz.getField(STRICT_MODE.symbolName()).getBoolean(null);
            } catch (final NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                //ignored
                strictFlag = false;
            }
        }

        // In strict mode, eval does not instantiate variables and functions
        // in the caller's environment. A new environment is created!
        if (strictFlag) {
            // Create a new scope object
            final ScriptObject strictEvalScope = ((GlobalObject)global).newObject();

            // bless it as a "scope"
            strictEvalScope.setIsScope();

            // set given scope to be it's proto so that eval can still
            // access caller environment vars in the new environment.
            strictEvalScope.setProto(scope);
            scope = strictEvalScope;
        }

        ScriptFunction func = getRunScriptFunction(clazz, scope);
        Object evalThis;
        if (directEval) {
            evalThis = (callThis instanceof ScriptObject || strictFlag) ? callThis : global;
        } else {
            evalThis = global;
        }

        return ScriptRuntime.apply(func, evalThis);
    }