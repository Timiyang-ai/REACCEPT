@Override
	public final List<TestExecutionListener> getTestExecutionListeners() {
		Class<?> clazz = getBootstrapContext().getTestClass();
		Class<TestExecutionListeners> annotationType = TestExecutionListeners.class;
		List<Class<? extends TestExecutionListener>> classesList = new ArrayList<>();
		boolean usingDefaults = false;

		AnnotationDescriptor<TestExecutionListeners> descriptor =
				MetaAnnotationUtils.findAnnotationDescriptor(clazz, annotationType);

		// Use defaults?
		if (descriptor == null) {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("@TestExecutionListeners is not present for class [%s]: using defaults.",
						clazz.getName()));
			}
			usingDefaults = true;
			classesList.addAll(getDefaultTestExecutionListenerClasses());
		}
		else {
			// Traverse the class hierarchy...
			while (descriptor != null) {
				Class<?> declaringClass = descriptor.getDeclaringClass();
				TestExecutionListeners testExecutionListeners = descriptor.synthesizeAnnotation();
				if (logger.isTraceEnabled()) {
					logger.trace(String.format("Retrieved @TestExecutionListeners [%s] for declaring class [%s].",
							testExecutionListeners, declaringClass.getName()));
				}

				boolean inheritListeners = testExecutionListeners.inheritListeners();
				AnnotationDescriptor<TestExecutionListeners> superDescriptor =
						MetaAnnotationUtils.findAnnotationDescriptor(
								descriptor.getRootDeclaringClass().getSuperclass(), annotationType);

				// If there are no listeners to inherit, we might need to merge the
				// locally declared listeners with the defaults.
				if ((!inheritListeners || superDescriptor == null) &&
						testExecutionListeners.mergeMode() == MergeMode.MERGE_WITH_DEFAULTS) {
					if (logger.isDebugEnabled()) {
						logger.debug(String.format("Merging default listeners with listeners configured via " +
								"@TestExecutionListeners for class [%s].", descriptor.getRootDeclaringClass().getName()));
					}
					usingDefaults = true;
					classesList.addAll(getDefaultTestExecutionListenerClasses());
				}

				classesList.addAll(0, Arrays.asList(testExecutionListeners.listeners()));

				descriptor = (inheritListeners ? superDescriptor : null);
			}
		}

		Collection<Class<? extends TestExecutionListener>> classesToUse = classesList;
		// Remove possible duplicates if we loaded default listeners.
		if (usingDefaults) {
			classesToUse = new LinkedHashSet<>(classesList);
		}

		List<TestExecutionListener> listeners = instantiateListeners(classesToUse);
		// Sort by Ordered/@Order if we loaded default listeners.
		if (usingDefaults) {
			AnnotationAwareOrderComparator.sort(listeners);
		}

		if (logger.isInfoEnabled()) {
			logger.info("Using TestExecutionListeners: " + listeners);
		}
		return listeners;
	}