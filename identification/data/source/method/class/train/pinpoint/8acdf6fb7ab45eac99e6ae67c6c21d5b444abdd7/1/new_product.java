public void initLocalVariables(final InsnList instructions) {
        // find enter & exit instruction.
        final LabelNode variableStartLabelNode = new LabelNode();
        final LabelNode variableEndLabelNode = new LabelNode();
        if(instructions.getFirst() != null) {
            instructions.insertBefore(instructions.getFirst(), variableStartLabelNode);
        } else {
            instructions.insert(variableStartLabelNode);
        }
        instructions.insert(instructions.getLast(), variableEndLabelNode);

        if (!isStatic()) {
            addLocalVariable("this", Type.getObjectType(this.declaringClassInternalName).getDescriptor(), variableStartLabelNode, variableEndLabelNode);
        }

        for (Type type : this.argumentTypes) {
            addLocalVariable(JavaAssistUtils.javaClassNameToVariableName(type.getClassName()), type.getDescriptor(), variableStartLabelNode, variableEndLabelNode);
        }
    }