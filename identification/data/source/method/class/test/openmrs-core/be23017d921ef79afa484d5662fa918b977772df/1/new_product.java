@SuppressWarnings("unchecked")
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String methodName = method.getName();
		
		// skip out early if there are no arguments
		if (args == null || args.length == 0)
			return;
		
		Object mainArgument = args[0];
		
		// fail early on a null parameter
		if (mainArgument == null)
			return;

		// the "create" is there to cover old deprecated methods since AOP doesn't occur
		// on method calls within a class, only on calls to methods from external classes to methods
		// "update" is not an option here because there are multiple methods that start with "update" but is
		// not updating the primary argument. eg: ConceptService.updateConceptWord(Concept)
		if (methodName.startsWith("save") || methodName.startsWith("create")) {
			
			// if a second argument exists, pass that to the save handler as well
			// (with current code, it means we're either in an obs save or a user save)
			String other = null;
			if (args.length > 1) {
				other = (String) args[1];
			}
			
			// if the first argument is an OpenmrsObject, handle it now
			Reflect reflect = new Reflect(OpenmrsObject.class);
			
			if (reflect.isSuperClass(mainArgument)) {
				recursivelyHandle(SaveHandler.class, (OpenmrsObject) mainArgument, other);
			}
			// if the first argument is a list of openmrs objects, handle them all now
			else if (Reflect.isCollection(mainArgument) && isOpenmrsObjectCollection(mainArgument)) {
				Collection<OpenmrsObject> openmrsObjects = (Collection<OpenmrsObject>) mainArgument;
				
				for (OpenmrsObject object : openmrsObjects) {
					recursivelyHandle(SaveHandler.class, object, other);
				}
				
			}
			
		} else {
			// fail early if the method name is not like retirePatient or retireConcept when dealing
			// with Patients or Concepts as the first argument
			if (!methodNameEndsWithClassName(method, mainArgument.getClass()))
				return;
			
			if (methodName.startsWith("void")) {
				Voidable voidable = (Voidable) args[0];
				String voidReason = (String) args[1];
				recursivelyHandle(VoidHandler.class, voidable, voidReason);
				
			} else if (methodName.startsWith("unvoid")) {
				Voidable voidable = (Voidable) args[0];
				Date originalDateVoided = voidable.getDateVoided();
				recursivelyHandle(UnvoidHandler.class, voidable, Context.getAuthenticatedUser(), originalDateVoided, null,
				    null);
				
			} else if (methodName.startsWith("retire")) {
				Retireable retirable = (Retireable) args[0];
				String retireReason = (String) args[1];
				recursivelyHandle(RetireHandler.class, retirable, retireReason);
				
			} else if (methodName.startsWith("unretire")) {
				Retireable retirable = (Retireable) args[0];
				Date originalDateRetired = retirable.getDateRetired();
				recursivelyHandle(UnretireHandler.class, retirable, Context.getAuthenticatedUser(), originalDateRetired,
				    null, null);
				
			}
		}
		
	}