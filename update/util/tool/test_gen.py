import os

path = "output/gpt-4-0125-preview/source"

for root, dirs, files in os.walk(path):
    for file in files:
        file_path = os.path.join(root, file)
        print(file_path)

        f = open(file_path, "r", encoding="utf-8")
        log = f.read()
        try:
            test_gen = log.split(
                "----------------------------------------------------------------\ngenerated test\n")[1].split(
                "\n----------------------------------------------------------------")[0].strip()
        except IndexError:
            # cut manually before
            test_gen = log.split(
                "----------------------------------------------------------------\n测试代码\n")[1].split(
                "\n----------------------------------------------------------------")[0].strip()
        f.close()

        f = open(file_path, "w", encoding="utf-8")
        f.write(test_gen)
        f.close()

        os.rename(file_path, file_path[:-3] + "java")
