static
	public Function getFunction(String name){

		if(name == null){
			return null;
		} // End if

		if(FunctionRegistry.functions.containsKey(name)){
			Function function = FunctionRegistry.functions.get(name);

			return function;
		}

		Class<?> functionClazz;

		if(FunctionRegistry.functionClazzes.containsKey(name)){
			functionClazz = FunctionRegistry.functionClazzes.get(name);
		} else

		{
			functionClazz = loadFunctionClass(name);

			FunctionRegistry.functionClazzes.put(name, functionClazz);
		} // End if

		if(functionClazz != null){
			Function function;

			try {
				function = (Function)functionClazz.newInstance();
			} catch(IllegalAccessException | InstantiationException | ExceptionInInitializerError e){
				throw (EvaluationException)new EvaluationException()
					.initCause(e);
			}

			return function;
		}

		return null;
	}