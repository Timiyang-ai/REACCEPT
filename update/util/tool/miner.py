import json
import os
import re

from util.tool.source import Source
from util.tool.spider import Spider


class Miner:
    # data_set = "data/set"

    # ceprot = "data/set/CEPROT"
    # ceprot = "data/set/CEPROT/gen"
    # ceprot = "data/set/CEPROT/gen/train.json"
    ceprot = "data/set/CEPROT/gen/test.json"

    # reaccept = "data/set/REACCEPT"
    # reaccept = "data/set/REACCEPT/train"
    reaccept = "data/set/REACCEPT/test"

    @classmethod
    def mine_ceprot(cls):
        json_file = open(cls.ceprot, mode="r", encoding="utf-8")
        samples = json.load(json_file)
        json_file.close()
        total = len(samples)
        print(total)

        count = 0
        for sample in samples:
            label = sample["label"]
            db_product = sample["focal_db"]
            db_test = sample["test_db"]

            commit_new_product = db_product[3]
            commit_new_test = db_test[3]

            group_project = db_test[1]

            path_old_product = db_product[5]
            path_old_test = db_test[5]
            path_new_product = db_product[7]
            path_new_test = db_test[7]

            project = group_project.split("/")[1]

            commit_old_product = commit_new_product + "^"
            commit_old_test = commit_new_test + "^"

            old_product = "/".join([Spider.spider, group_project, commit_old_product, path_old_product])
            old_test = "/".join([Spider.spider, group_project, commit_old_test, path_old_test])
            new_product = "/".join([Spider.spider, group_project, commit_new_product, path_new_product])
            new_test = "/".join([Spider.spider, group_project, commit_new_test, path_new_test])

            spider = Spider()
            spider.set(old_product, old_test, new_product, new_test)
            source_ = spider.crawl_sample()

            if not os.path.exists("/".join([Source.source, project, commit_new_test])):
                os.makedirs("/".join([Source.source, project, commit_new_test]))
            numbers = os.listdir("/".join([Source.source, project, commit_new_test]))
            number = str(len(numbers) + 1)

            source = Source()
            source.set(project, commit_new_test, number, label, source_[0], source_[1], source_[2], source_[3])
            source.store()

            count += 1
            print(count, "/", total)

    @classmethod
    def mine_ceprot_retry(cls, break_point):
        json_file = open(cls.ceprot, mode="r", encoding="utf-8")
        samples = json.load(json_file)
        json_file.close()
        total = len(samples)
        print(total)

        # count = 0
        for count in range(break_point, total):
            sample = samples[count]

            label = sample["label"]
            db_product = sample["focal_db"]
            db_test = sample["test_db"]

            commit_new_product = db_product[3]
            commit_new_test = db_test[3]

            group_project = db_test[1]

            path_old_product = db_product[5]
            path_old_test = db_test[5]
            path_new_product = db_product[7]
            path_new_test = db_test[7]

            project = group_project.split("/")[1]

            commit_old_product = commit_new_product + "^"
            commit_old_test = commit_new_test + "^"

            old_product = "/".join([Spider.spider, group_project, commit_old_product, path_old_product])
            old_test = "/".join([Spider.spider, group_project, commit_old_test, path_old_test])
            new_product = "/".join([Spider.spider, group_project, commit_new_product, path_new_product])
            new_test = "/".join([Spider.spider, group_project, commit_new_test, path_new_test])

            spider = Spider()
            spider.set(old_product, old_test, new_product, new_test)
            source_ = spider.crawl_sample()

            if not os.path.exists("/".join([Source.source, project, commit_new_test])):
                os.makedirs("/".join([Source.source, project, commit_new_test]))
            numbers = os.listdir("/".join([Source.source, project, commit_new_test]))
            number = str(len(numbers) + 1)

            source = Source()
            source.set(project, commit_new_test, number, label, source_[0], source_[1], source_[2], source_[3])
            source.store()

            count += 1
            print(count, "/", total)

    @classmethod
    def mine_ceprot_method(cls):
        json_file = open(cls.ceprot, mode="r", encoding="utf-8")
        samples = json.load(json_file)
        json_file.close()
        total = len(samples)
        print(total)

        count = 0
        for sample in samples:
            label = sample["label"]
            db_product = sample["focal_db"]
            db_test = sample["test_db"]

            commit_new_test = db_test[3]

            group_project = db_test[1]

            source_old_product = db_product[8]
            source_old_test = db_test[8]
            source_new_product = db_product[10]
            source_new_test = db_test[10]

            project = group_project.split("/")[1]

            source_old_product_newline = re.sub("\r", "\n", re.sub("\r\n", "\n", source_old_product))
            source_old_test_newline = re.sub("\r", "\n", re.sub("\r\n", "\n", source_old_test))
            source_new_product_newline = re.sub("\r", "\n", re.sub("\r\n", "\n", source_new_product))
            source_new_test_newline = re.sub("\r", "\n", re.sub("\r\n", "\n", source_new_test))

            if not os.path.exists("/".join([Source.source, project, commit_new_test])):
                os.makedirs("/".join([Source.source, project, commit_new_test]))
            numbers = os.listdir("/".join([Source.source, project, commit_new_test]))
            number = str(len(numbers) + 1)

            source = Source()
            # source.set(
            #     project, commit_new_test, number, label,
            #     source_old_product, source_old_test,
            #     source_new_product, source_new_test)
            source.set(
                project, commit_new_test, number, label,
                source_old_product_newline, source_old_test_newline,
                source_new_product_newline, source_new_test_newline)
            source.store()

            count += 1
            print(count, "/", total)

    @classmethod
    def rebuild_ceprot(cls):
        json_file = open(cls.ceprot, mode="r", encoding="utf-8")
        samples = json.load(json_file)
        json_file.close()
        total = len(samples)
        print(total)

        count = 0
        for sample in samples:
            label = sample["label"]
            db_product = sample["focal_db"]
            db_test = sample["test_db"]

            commit_new_product = db_product[3]
            commit_new_test = db_test[3]

            group_project = db_test[1]

            path_old_product = db_product[5]
            path_old_test = db_test[5]
            path_new_product = db_product[7]
            path_new_test = db_test[7]

            group = group_project.split("/")[0]
            project = group_project.split("/")[1]

            commit_old_product = commit_new_product + "^"
            commit_old_test = commit_new_test + "^"

            class_old_product = path_old_product.split("/")[-1].split(".")[0]
            class_old_test = path_old_test.split("/")[-1].split(".")[0]
            class_new_product = path_new_product.split("/")[-1].split(".")[0]
            class_new_test = path_new_test.split("/")[-1].split(".")[0]

            project_commit = "/".join([project, commit_new_test])
            group_project_commit = "/".join([group, project, commit_new_test])

            sample_ = {
                "group": group,
                "project": project,
                "commit": {
                    "old": {
                        "product": commit_old_product,
                        "test": commit_old_test},
                    "new": {
                        "product": commit_new_product,
                        "test": commit_new_test}},
                "class": {
                    "old": {
                        "product": class_old_product,
                        "test": class_old_test},
                    "new": {
                        "product": class_new_product,
                        "test": class_new_test}},
                "group_project": group_project,
                "project_commit": project_commit,
                "group_project_commit": group_project_commit,
                "path": {
                    "old": {
                        "product": path_old_product,
                        "test": path_old_test},
                    "new": {
                        "product": path_new_product,
                        "test": path_new_test}},
                "label": label}

            if not os.path.exists("/".join([cls.reaccept, project, commit_new_test])):
                os.makedirs("/".join([cls.reaccept, project, commit_new_test]))
            numbers = os.listdir("/".join([cls.reaccept, project, commit_new_test]))
            number = str(len(numbers) + 1)
            os.makedirs("/".join([cls.reaccept, project, commit_new_test, number]))

            json_file = open(
                "/".join([cls.reaccept, project, commit_new_test, number, "sample.json"]),
                "w",
                encoding="utf-8")
            # json.dump(sample_, json_file)
            json.dump(sample_, json_file, indent=4)
            json_file.close()

            count += 1
            print(count, "/", total)


if __name__ == "__main__":
    # mine
    # ceprot
    # Miner.mine_ceprot()
    # Miner.mine_ceprot_retry(0)
    Miner.mine_ceprot_method()

    # rebuild
    # ceprot
    Miner.rebuild_ceprot()

    print("Done")
