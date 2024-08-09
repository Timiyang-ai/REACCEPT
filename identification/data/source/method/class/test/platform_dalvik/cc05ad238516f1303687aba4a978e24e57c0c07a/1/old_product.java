public static PolicyNode getPolicyTree() {
        return new PolicyNode() {
            final PolicyNode parent = this;
            public int getDepth() {
                // parent
                return 0;
            }

            public boolean isCritical() {
                return false;
            }

            public String getValidPolicy() {
                return null;
            }

            public PolicyNode getParent() {
                return null;
            }

            public Iterator getChildren() {
                PolicyNode child = new PolicyNode() {
                    public int getDepth() {
                        // child
                        return 1;
                    }

                    public boolean isCritical() {
                        return false;
                    }

                    public String getValidPolicy() {
                        return null;
                    }

                    public PolicyNode getParent() {
                        return parent;
                    }

                    public Iterator getChildren() {
                        return null;
                    }

                    public Set getExpectedPolicies() {
                        return null;
                    }

                    public Set getPolicyQualifiers() {
                        return null;
                    }
                };
                HashSet<PolicyNode> s = new HashSet<PolicyNode>();
                s.add(child);
                return s.iterator();
            }

            public Set getExpectedPolicies() {
                return null;
            }

            public Set getPolicyQualifiers() {
                return null;
            }
        };
    }