@SuppressWarnings("unchecked")
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String methodName = method.getName();
		
		// the "create" and "update" are in to cover old deprecated methods since AOP doesn't occur
		// on method calls within a class, only on calls to methods from external classes to methods
		if (methodName.startsWith("save") || methodName.startsWith("create") || methodName.startsWith("update")) {
			Object mainArgument = args[0];
			
			// fail early on a null parameter
			if (mainArgument == null)
				return;
			
			Class<?> argClass = mainArgument.getClass();
			
			// if a second argument exists, pass that to the save handler as well
			// (with current code, it means we're either in an obs save or a user save)
			String other = null;
			if (args.length > 1) {
				other = (String) args[1];
			}
			
			// if the first argument is an OpenmrsObject, handle it now
			if (OpenmrsObject.class.isAssignableFrom(argClass)) {
				recursivelyHandle(SaveHandler.class, (OpenmrsObject) mainArgument, other);
			}
			// if the first argument is a list of openmrs objects, handle them all now
			else if (isOpenmrsObjectCollection(argClass, mainArgument)) {
				Collection<OpenmrsObject> openmrsObjects = (Collection<OpenmrsObject>) mainArgument;
				
				for (OpenmrsObject object : openmrsObjects) {
					recursivelyHandle(SaveHandler.class, object, other);
				}
				
			}
			
		} else if (methodName.startsWith("void")) {
			Voidable voidable = (Voidable) args[0];
			String voidReason = (String) args[1];
			recursivelyHandle(VoidHandler.class, voidable, voidReason);
			
		} else if (methodName.startsWith("unvoid")) {
			Voidable voidable = (Voidable) args[0];
			Date originalDateVoided = voidable.getDateVoided();
			recursivelyHandle(UnvoidHandler.class, voidable, Context.getAuthenticatedUser(), originalDateVoided, null, null);
			
		} else if (methodName.startsWith("retire")) {
			Retireable retirable = (Retireable) args[0];
			String retireReason = (String) args[1];
			recursivelyHandle(RetireHandler.class, retirable, retireReason);
			
		} else if (methodName.startsWith("unretire")) {
			Retireable retirable = (Retireable) args[0];
			Date originalDateRetired = retirable.getDateRetired();
			recursivelyHandle(UnretireHandler.class, retirable, Context.getAuthenticatedUser(), originalDateRetired, null, null);
			
		}
		
	}