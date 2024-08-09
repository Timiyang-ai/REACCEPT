import re

import requests


class Spider:
    spider = "https://raw.githubusercontent.com"

    @staticmethod
    def crawl_url(url):
        source = requests.get(url, verify=True).text
        source_newline = re.sub("\r", "\n", re.sub("\r\n", "\n", source))
        # return source
        return source_newline

    def __init__(self):
        self.old_product = None
        self.old_test = None
        self.new_product = None
        self.new_test = None

    def set(self, old_product, old_test, new_product, new_test):
        self.old_product = old_product
        self.old_test = old_test
        self.new_product = new_product
        self.new_test = new_test

    def crawl_sample(self):
        old_product = self.crawl_url(self.old_product)
        old_test = self.crawl_url(self.old_test)
        new_product = self.crawl_url(self.new_product)
        new_test = self.crawl_url(self.new_test)
        return old_product, old_test, new_product, new_test
