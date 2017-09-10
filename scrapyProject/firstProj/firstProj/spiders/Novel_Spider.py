import scrapy

class NovelSpider(scrapy.Spider):
    name = "novel"
    allowed_domains = ["biqudu.com"]
    start_urls = [
        "http://www.biqudu.com/21_21470/",
        "http://www.biqudu.com/0_68/"
    ]

    def parse(self, response):
        for urlItem in response.xpath('//div[@id="list"]/dl/dd/a/@href'):
            print(urlItem.extract())
        filebname = response.url.split("/")[-2]
        with open(filebname,"wb") as f:
            f.write(response.body)