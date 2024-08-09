import os


class Source:
    # source = "data/source"
    # source = "data/source/train"
    source = "data/source/test"

    def __init__(self):
        self.project = None
        self.commit = None
        self.number = None
        self.label = None
        self.old_product = None
        self.old_test = None
        self.new_product = None
        self.new_test = None

    def set(self, project, commit, number, label, old_product, old_test, new_product, new_test):
        self.project = project
        self.commit = commit
        self.number = number
        self.label = label
        self.old_product = old_product
        self.old_test = old_test
        self.new_product = new_product
        self.new_test = new_test

    def load(self, project, commit, number):
        self.project = project
        self.commit = commit
        self.number = number

        file = open(
            "/".join([self.source, self.project, self.commit, self.number, "label.txt"]),
            "r",
            encoding="utf-8")
        self.label = file.read()
        file.close()

        file = open(
            "/".join([self.source, self.project, self.commit, self.number, "old_product.java"]),
            "r",
            encoding="utf-8")
        self.old_product = file.read()
        file.close()

        file = open(
            "/".join([self.source, self.project, self.commit, self.number, "old_test.java"]),
            "r",
            encoding="utf-8")
        self.old_test = file.read()
        file.close()

        file = open(
            "/".join([self.source, self.project, self.commit, self.number, "new_product.java"]),
            "r",
            encoding="utf-8")
        self.new_product = file.read()
        file.close()

        file = open(
            "/".join([self.source, self.project, self.commit, self.number, "new_test.java"]),
            "r",
            encoding="utf-8")
        self.new_test = file.read()
        file.close()

    def store(self):
        if not os.path.exists("/".join([self.source, self.project, self.commit, self.number])):
            os.makedirs("/".join([self.source, self.project, self.commit, self.number]))

        file = open(
            "/".join([self.source, self.project, self.commit, self.number, "label.txt"]),
            "w",
            encoding="utf-8")
        file.write(str(self.label))
        file.close()

        file = open(
            "/".join([self.source, self.project, self.commit, self.number, "old_product.java"]),
            "w",
            encoding="utf-8")
        file.write(self.old_product)
        file.close()

        file = open(
            "/".join([self.source, self.project, self.commit, self.number, "old_test.java"]),
            "w",
            encoding="utf-8")
        file.write(self.old_test)
        file.close()

        file = open(
            "/".join([self.source, self.project, self.commit, self.number, "new_product.java"]),
            "w",
            encoding="utf-8")
        file.write(self.new_product)
        file.close()

        file = open(
            "/".join([self.source, self.project, self.commit, self.number, "new_test.java"]),
            "w",
            encoding="utf-8")
        file.write(self.new_test)
        file.close()
