@Override
			public Subject login()
			{
				return new DefaultSubject()
				{
					private static final long serialVersionUID = 1L;

					/**
					 * 
					 * @see org.wicketstuff.security.hive.authentication.DefaultSubject#isClassAuthenticated(java.lang.Class)
					 */
					@Override
					public boolean isClassAuthenticated(Class< ? > class1)
					{
						return false;
					}

					/**
					 * 
					 * @see org.wicketstuff.security.hive.authentication.DefaultSubject#isComponentAuthenticated(org.apache.wicket.Component)
					 */
					@Override
					public boolean isComponentAuthenticated(Component component)
					{
						return false;
					}

					/**
					 * 
					 * @see org.wicketstuff.security.hive.authentication.DefaultSubject#isModelAuthenticated(org.apache.wicket.model.IModel,
					 *      org.apache.wicket.Component)
					 */
					@Override
					public boolean isModelAuthenticated(IModel< ? > model, Component component)
					{
						return false;
					}
				};
			}