static
	public Function getFunction(String name){

		if(name == null){
			return null;
		} // End if

		if(FunctionRegistry.pmmlFunctions.containsKey(name)){
			Function function = FunctionRegistry.pmmlFunctions.get(name);

			return function;
		} // End if

		if(FunctionRegistry.userDefinedFunctions.containsKey(name)){
			Function function = FunctionRegistry.userDefinedFunctions.get(name);

			return function;
		}

		Class<?> functionClazz;

		if(FunctionRegistry.userDefinedFunctionClazzes.containsKey(name)){
			functionClazz = FunctionRegistry.userDefinedFunctionClazzes.get(name);
		} else

		{
			functionClazz = loadFunctionClass(name);

			FunctionRegistry.userDefinedFunctionClazzes.put(name, functionClazz);
		} // End if

		if(functionClazz != null){
			Function function;

			try {
				function = (Function)functionClazz.newInstance();
			} catch(IllegalAccessException | InstantiationException | ExceptionInInitializerError e){
				throw new EvaluationException("Function class " + PMMLException.formatKey(functionClazz.getName()) + " could not be instantiated")
					.initCause(e);
			}

			return function;
		}

		return null;
	}