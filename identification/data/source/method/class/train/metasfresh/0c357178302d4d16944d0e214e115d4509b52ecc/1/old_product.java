final ProcessInfoParameter createProcessInfoParameter(final I_AD_PInstance_Para adPInstancePara)
	{
		final String ParameterName = adPInstancePara.getParameterName();
		// Info
		final String Info = adPInstancePara.getInfo();
		final String Info_To = adPInstancePara.getInfo_To();

		//
		// String
		Object Parameter = adPInstancePara.getP_String();
		Object Parameter_To = adPInstancePara.getP_String_To();

		//
		// Timestamp
		if (Parameter == null && Parameter_To == null)
		{
			Parameter = adPInstancePara.getP_Date();
			Parameter_To = adPInstancePara.getP_Date_To();
		}

		//
		// Big Decimal
		// NOTE: keep this one last because getP_Number() is always returning not null
		if (Parameter == null && Parameter_To == null
				&& (!InterfaceWrapperHelper.isNull(adPInstancePara, I_AD_PInstance_Para.COLUMNNAME_P_Number)
						|| !InterfaceWrapperHelper.isNull(adPInstancePara, I_AD_PInstance_Para.COLUMNNAME_P_Number_To)))
		{
			Parameter = adPInstancePara.getP_Number();
			if (InterfaceWrapperHelper.isNull(adPInstancePara, I_AD_PInstance_Para.COLUMNNAME_P_Number_To))
			{
				Parameter_To = null;
			}
			else
			{
				Parameter_To = adPInstancePara.getP_Number_To();
			}
		}
		//
		final ProcessInfoParameter param = new ProcessInfoParameter(ParameterName, Parameter, Parameter_To, Info, Info_To);
		return param;
	}