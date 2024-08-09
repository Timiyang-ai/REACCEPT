protected final CharSequence renderAjaxAttributes(final Component component,
		AjaxRequestAttributes attributes)
	{
		JSONObject attributesJson = new JSONObject();

		try
		{
			String formId = attributes.getFormId();
			if (Strings.isEmpty(formId) == false)
			{
				attributesJson.put(AjaxAttributeName.FORM_ID.jsonName(), formId);
			}
			attributesJson.put(AjaxAttributeName.URL.jsonName(), getCallbackUrl());

			String[] eventNames = attributes.getEventNames();
			if (eventNames.length == 1)
			{
				attributesJson.put(AjaxAttributeName.EVENT_NAME.jsonName(), eventNames[0]);
			}
			else
			{
				for (String eventName : eventNames)
				{
					attributesJson.append(AjaxAttributeName.EVENT_NAME.jsonName(), eventName);
				}
			}

			if (component instanceof Page == false)
			{
				String componentId = component.getMarkupId();
				attributesJson.put(AjaxAttributeName.MARKUP_ID.jsonName(), componentId);
			}

			if (attributes.isMultipart())
			{
				attributesJson.put(AjaxAttributeName.IS_MULTIPART.jsonName(), true);
			}

			String submittingComponentId = attributes.getSubmittingComponentName();
			if (Strings.isEmpty(submittingComponentId) == false)
			{
				attributesJson.put(AjaxAttributeName.SUBMITTING_COMPONENT_NAME.jsonName(),
					submittingComponentId);
			}

			CharSequence childSelector = attributes.getChildSelector();
			if (Strings.isEmpty(childSelector) == false)
			{
				attributesJson.put(AjaxAttributeName.CHILD_SELECTOR.jsonName(), childSelector);
			}

			String indicatorId = findIndicatorId();
			if (Strings.isEmpty(indicatorId) == false)
			{
				attributesJson.put(AjaxAttributeName.INDICATOR_ID.jsonName(), indicatorId);
			}

			for (IAjaxCallListener ajaxCallListener : attributes.getAjaxCallListeners())
			{
				if (ajaxCallListener != null)
				{
					CharSequence beforeHandler = ajaxCallListener.getBeforeHandler(component);
					appendListenerHandler(beforeHandler, attributesJson,
						AjaxAttributeName.BEFORE_HANDLER.jsonName(),
						BEFORE_HANDLER_FUNCTION_TEMPLATE);

					CharSequence beforeSendHandler = ajaxCallListener.getBeforeSendHandler(component);
					appendListenerHandler(beforeSendHandler, attributesJson,
						AjaxAttributeName.BEFORE_SEND_HANDLER.jsonName(),
						BEFORE_SEND_HANDLER_FUNCTION_TEMPLATE);

					CharSequence afterHandler = ajaxCallListener.getAfterHandler(component);
					appendListenerHandler(afterHandler, attributesJson,
						AjaxAttributeName.AFTER_HANDLER.jsonName(), AFTER_HANDLER_FUNCTION_TEMPLATE);

					CharSequence successHandler = ajaxCallListener.getSuccessHandler(component);
					appendListenerHandler(successHandler, attributesJson,
						AjaxAttributeName.SUCCESS_HANDLER.jsonName(),
						SUCCESS_HANDLER_FUNCTION_TEMPLATE);

					CharSequence failureHandler = ajaxCallListener.getFailureHandler(component);
					appendListenerHandler(failureHandler, attributesJson,
						AjaxAttributeName.FAILURE_HANDLER.jsonName(),
						FAILURE_HANDLER_FUNCTION_TEMPLATE);

					CharSequence completeHandler = ajaxCallListener.getCompleteHandler(component);
					appendListenerHandler(completeHandler, attributesJson,
						AjaxAttributeName.COMPLETE_HANDLER.jsonName(),
						COMPLETE_HANDLER_FUNCTION_TEMPLATE);

					CharSequence precondition = ajaxCallListener.getPrecondition(component);
					appendListenerHandler(precondition, attributesJson,
						AjaxAttributeName.PRECONDITION.jsonName(), PRECONDITION_FUNCTION_TEMPLATE);
				}
			}

			JSONArray extraParameters = JsonUtils.asArray(attributes.getExtraParameters());

			if (extraParameters.length() > 0)
			{
				attributesJson.put(AjaxAttributeName.EXTRA_PARAMETERS.jsonName(), extraParameters);
			}

			List<CharSequence> dynamicExtraParameters = attributes.getDynamicExtraParameters();
			if (dynamicExtraParameters != null)
			{
				for (CharSequence dynamicExtraParameter : dynamicExtraParameters)
				{
					String func = String.format(DYNAMIC_PARAMETER_FUNCTION_TEMPLATE,
						dynamicExtraParameter);
					JsonFunction function = new JsonFunction(func);
					attributesJson.append(AjaxAttributeName.DYNAMIC_PARAMETER_FUNCTION.jsonName(),
						function);
				}
			}

			if (attributes.isAsynchronous() == false)
			{
				attributesJson.put(AjaxAttributeName.IS_ASYNC.jsonName(), false);
			}

			AjaxChannel channel = attributes.getChannel();
			if (channel != null && channel.equals(AjaxChannel.DEFAULT) == false)
			{
				attributesJson.put(AjaxAttributeName.CHANNEL.jsonName(), channel);
			}

			if (attributes.isAllowDefault())
			{
				attributesJson.put(AjaxAttributeName.IS_ALLOW_DEFAULT.jsonName(), true);
			}

			Method method = attributes.getMethod();
			if (Method.POST == method)
			{
				attributesJson.put(AjaxAttributeName.METHOD.jsonName(), method);
			}

			if (AjaxRequestAttributes.EventPropagation.BUBBLE.equals(attributes.getEventPropagation()))
			{
				attributesJson.put(AjaxAttributeName.EVENT_PROPAGATION.jsonName(), "bubble");
			}
			else if (AjaxRequestAttributes.EventPropagation.STOP_IMMEDIATE.equals(attributes.getEventPropagation()))
			{
				attributesJson.put(AjaxAttributeName.EVENT_PROPAGATION.jsonName(), "stopImmediate");
			}

			Duration requestTimeout = attributes.getRequestTimeout();
			if (requestTimeout != null)
			{
				attributesJson.put(AjaxAttributeName.REQUEST_TIMEOUT.jsonName(),
					requestTimeout.getMilliseconds());
			}

			boolean wicketAjaxResponse = attributes.isWicketAjaxResponse();
			if (wicketAjaxResponse == false)
			{
				attributesJson.put(AjaxAttributeName.IS_WICKET_AJAX_RESPONSE.jsonName(), false);
			}

			String dataType = attributes.getDataType();
			if (AjaxRequestAttributes.XML_DATA_TYPE.equals(dataType) == false)
			{
				attributesJson.put(AjaxAttributeName.DATATYPE.jsonName(), dataType);
			}

			ThrottlingSettings throttlingSettings = attributes.getThrottlingSettings();
			if (throttlingSettings != null)
			{
				JSONObject throttlingSettingsJson = new JSONObject();
				throttlingSettingsJson.put(AjaxAttributeName.THROTTLING_ID.jsonName(),
					throttlingSettings.getId());
				throttlingSettingsJson.put(AjaxAttributeName.THROTTLING_DELAY.jsonName(),
					throttlingSettings.getDelay().getMilliseconds());
				if (throttlingSettings.getPostponeTimerOnUpdate())
				{
					throttlingSettingsJson.put(
						AjaxAttributeName.THROTTLING_POSTPONE_ON_UPDATE.jsonName(), true);
				}
				attributesJson.put(AjaxAttributeName.THROTTLING.jsonName(), throttlingSettingsJson);
			}

			postprocessConfiguration(attributesJson, component);
		}
		catch (JSONException e)
		{
			throw new WicketRuntimeException(e);
		}

		String attributesAsJson = attributesJson.toString();

		return attributesAsJson;
	}