protected static String substituteValues(String str, BackendAPI backend) {
        SystemStateBean bean = new SystemStateBean();
        backend.updateSystemState(bean);

        // Early exit if there is nothing to match.
        if (!str.contains("{")) {
            return str;
        }

        // Do simple substitutions
        String command = str;
        command = MACHINE_X.matcher(command).replaceAll(bean.getMachineX());
        command = MACHINE_Y.matcher(command).replaceAll(bean.getMachineY());
        command = MACHINE_Z.matcher(command).replaceAll(bean.getMachineZ());
        command = WORK_X.matcher(command).replaceAll(bean.getWorkX());
        command = WORK_Y.matcher(command).replaceAll(bean.getWorkY());
        command = WORK_Z.matcher(command).replaceAll(bean.getWorkZ());

        // Prompt for additional substitutions
        Matcher m = PROMPT_REGEX.matcher(command);
        List<String> prompts = new ArrayList<>();
        while (m.find()) {
            prompts.add(m.group(1));
        }

        if (prompts.size() > 0) {
            List<JTextField> fields = new ArrayList<>();
            JPanel myPanel = new JPanel();
            myPanel.setLayout(new MigLayout("wrap 2, width 200"));
            for (String s : prompts) {
                JTextField field = new JTextField();
                myPanel.add(new JLabel(s + ":"));
                myPanel.add(field, "growx, pushx");
                fields.add(field);
            }

            int result = JOptionPane.showConfirmDialog(null, myPanel, 
                     Localization.getString("macro.substitution"),
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                for(int i = 0; i < prompts.size(); i++) {
                    command = command.replace("{prompt|" + prompts.get(i) + "}", fields.get(i).getText());
                }
            } else {
                command = "";
            }
        }

        return command;
    }