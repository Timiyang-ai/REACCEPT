public void setReadOnly(boolean readOnly) {
        getBindings().stream().filter(binding -> binding.getSetter() != null)
                .forEach(binding -> binding.setReadOnly(readOnly));
    }