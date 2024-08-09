@SuppressWarnings("unchecked")
	public static <T extends IBaseResource> List<T> toListOfResourcesOfType(FhirContext theContext, IBaseBundle theBundle, Class<T> theTypeToInclude) {
		List<T> retVal = new ArrayList<T>();

		RuntimeResourceDefinition def = theContext.getResourceDefinition(theBundle);
		BaseRuntimeChildDefinition entryChild = def.getChildByName("entry");
		List<IBase> entries = entryChild.getAccessor().getValues(theBundle);

		BaseRuntimeElementCompositeDefinition<?> entryChildElem = (BaseRuntimeElementCompositeDefinition<?>) entryChild.getChildByName("entry");
		BaseRuntimeChildDefinition resourceChild = entryChildElem.getChildByName("resource");
		for (IBase nextEntry : entries) {
			for (IBase next : resourceChild.getAccessor().getValues(nextEntry)) {
				if (theTypeToInclude != null && !theTypeToInclude.isAssignableFrom(next.getClass())) {
					continue;
				}
				retVal.add((T) next);
			}
		}

		return retVal;
	}