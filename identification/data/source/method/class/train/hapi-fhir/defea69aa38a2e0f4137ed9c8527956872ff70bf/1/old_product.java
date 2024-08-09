public static List<IBaseResource> toListOfResourcesOfType(FhirContext theContext, IBaseBundle theBundle, Class<? extends IBaseResource> theTypeToInclude) {
		List<IBaseResource> retVal = new ArrayList<IBaseResource>();

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
				retVal.add((IBaseResource) next);
			}
		}

		return retVal;
	}