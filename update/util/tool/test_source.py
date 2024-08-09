import os
import shutil

source_path = "data/source/test"
old_test_path = "output/0-old/source"
new_test_path = "output/0-new/source"

projects = os.listdir(source_path)
total_project = len(projects)
count_project = 0
for project in projects:
    commits = os.listdir("/".join([source_path, project]))
    total_commit = len(commits)
    count_commit = 0
    for commit in commits:
        numbers = os.listdir("/".join([source_path, project, commit]))
        total_number = len(numbers)
        count_number = 0
        for number in numbers:
            shutil.copy(
                "/".join([source_path, project, commit, number, "old_test.java"]),
                "/".join([old_test_path, ".".join(["-".join([project, commit, number]), "java"])]))
            shutil.copy(
                "/".join([source_path, project, commit, number, "new_test.java"]),
                "/".join([new_test_path, ".".join(["-".join([project, commit, number]), "java"])]))
            count_number += 1
            print("\t\t\t", count_number, "/", total_number)
        count_commit += 1
        print("\t\t", count_commit, "/", total_commit)
    count_project += 1
    print("\t", count_project, "/", total_project)
