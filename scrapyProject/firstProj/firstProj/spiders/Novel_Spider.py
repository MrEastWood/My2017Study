import scrapy
from items import NovelItem
class NovelSpider(scrapy.Spider):

    name = "novel"
    allowed_domains = ["biqudu.com"]
    start_urls = [
        "http://www.biqudu.com/21_21470/"
    ]
    file1 = open("不朽凡人.txt", 'w')
    def parse(self, response):
        rootUrl = 'http://www.biqudu.com'
        for urlItem in response.xpath('//div[@id="list"]/dl/dd/a/@href'):
            print(urlItem.extract())
            url = rootUrl + urlItem.extract()
            print(url)
            yield scrapy.Request(url, self.getCaption)
        #filebname = response.url.split("/")[-2]
        #with open(filebname,"wb") as f:
        #    f.write(response.body)

    def getCaption(self, response):
        print("########################");
        item = NovelItem()
        item['url'] = response.url
        item['title'] = response.xpath('//div[@class="bookname"]/h1/text()').extract()
        item['content'] = response.xpath('//div[@id="content"]/text()').extract()
        print(item['title'][0])
        for context in  response.xpath('//div[@id="content"]/text()'):
            self.file1.write(context.extract())

