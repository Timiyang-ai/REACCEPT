public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			if (loginTabPane.getSelectedIndex() == 0)
				connectionOK();		//	first ok
			else
			{
				m_okPressed = true;
				defaultsOK();	//	disposes
			}
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			appExit();
		//
		else if (e.getSource() == hostField)
			validateConnection();
		else if (e.getSource() == languageCombo)
			languageComboChanged();
		//
		else if (e.getSource() == roleCombo)
			roleComboChanged();
		else if (e.getSource() == clientCombo)
			clientComboChanged();
		else if (e.getSource() == orgCombo)
			orgComboChanged();
	}