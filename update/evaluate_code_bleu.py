import csv
import os

from codebleu import calc_codebleu

new_test_dir = "data/source/test"
generation_test_dir = "output/gpt-4-0125-preview/source"
evaluation_codebleu = "output/gpt-4-0125-preview/codebleu.csv"

output = open(evaluation_codebleu, "w", encoding="utf-8", newline='')
csv_writer = csv.writer(output, delimiter=',')
csv_writer.writerow(
    ["Project", "Commit", "Number", "CodeBLEU", "BLEU", "Weighted BLEU", "Syntax Match", "Dataflow Match"])

total = [0, 0, 0, 0, 0]
generation_test_files = os.listdir(generation_test_dir)
for generation_test_file in generation_test_files:
    project_commit_number = generation_test_file.split(".")[0].split("-")
    project = "-".join(project_commit_number[:-2])
    commit = project_commit_number[-2]
    number = project_commit_number[-1]
    new_test_file = "/".join([project, commit, number, "new_test.java"])

    f = open("/".join([new_test_dir, new_test_file]), "r", encoding="utf-8")
    new_test_source = f.read()
    f.close()

    f = open("/".join([generation_test_dir, generation_test_file]), "r", encoding="utf-8")
    generation_test_source = f.read()
    f.close()

    result = calc_codebleu(
        [new_test_source], [generation_test_source],
        lang="java", weights=(0.25, 0.25, 0.25, 0.25), tokenizer=None)
    csv_writer.writerow([
        project,
        commit,
        number,
        result["codebleu"],
        result["ngram_match_score"],
        result["weighted_ngram_match_score"],
        result["syntax_match_score"],
        result["dataflow_match_score"]])
    total[0] += result["codebleu"]
    total[1] += result["ngram_match_score"]
    total[2] += result["weighted_ngram_match_score"]
    total[3] += result["syntax_match_score"]
    total[4] += result["dataflow_match_score"]
csv_writer.writerow(["Average", "", "", *(e / len(generation_test_files) for e in total)])
output.close()
