public void setInvoice(
			int C_Invoice_ID,
			int C_Currency_ID,
			BigDecimal GrandTotal, BigDecimal Open,
			BigDecimal FeeAmount,
			int DaysDue, 
			boolean IsInDispute,
			int TimesDunned, 
			int DaysAfterLast)
	{
		setC_Invoice_ID(C_Invoice_ID);
		m_C_CurrencyFrom_ID = C_Currency_ID;
		setAmt(GrandTotal);
		setOpenAmt(Open);
		setFeeAmt(FeeAmount);
		setConvertedAmt(Services.get(ICurrencyBL.class).convert(
				getOpenAmt(),
				CurrencyId.ofRepoId(C_Currency_ID), 
				CurrencyId.ofRepoId(getC_CurrencyTo_ID()), 
				ClientId.ofRepoId(getAD_Client_ID()), 
				OrgId.ofRepoId(getAD_Org_ID())));
		setIsInDispute(IsInDispute);
		setDaysDue(DaysDue);
		setTimesDunned(TimesDunned);
	}