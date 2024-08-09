static
	public Function getFunction(String name){
		Function function = FunctionRegistry.functions.get(name);
		if(function == null){
			function = loadJavaFunction(name);
		}

		return function;
	}