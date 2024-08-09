	void jbInit() throws Exception
	{
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		mainPanel.setLayout(mainLayout);
		bOK.setText("Exit");
		bOK.addActionListener(this);
		info.setBackground(AdempierePLAF.getFieldBackground_Inactive());
		southPanel.setLayout(southLayout);
		southLayout.setAlignment(FlowLayout.RIGHT);
		infoPane.setPreferredSize(new Dimension(400, 400));
		getContentPane().add(mainPanel);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		southPanel.add(bOK, null);
		mainPanel.add(infoPane, BorderLayout.CENTER);
		infoPane.getViewport().add(info, null);
	}   //  jbInit