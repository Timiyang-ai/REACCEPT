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

            public Iterator<PolicyNode> getChildren() {
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

                    public Iterator<PolicyNode> getChildren() {
                        return null;
                    }

                    public Set<String> getExpectedPolicies() {
                        return null;
                    }

                    public Set<? extends PolicyQualifierInfo> getPolicyQualifiers() {
                        return null;
                    }
                };
                HashSet<PolicyNode> s = new HashSet<PolicyNode>();
                s.add(child);
                return s.iterator();
            }

            public Set<String> getExpectedPolicies() {
                return null;
            }

            public Set<? extends PolicyQualifierInfo> getPolicyQualifiers() {
                return null;
            }
        };
    }