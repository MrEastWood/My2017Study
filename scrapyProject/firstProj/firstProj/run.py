from scrapy.crawler import CrawlerProcess
from scrapy.utils.project import get_project_settings
from spiders.Novel_Spider import NovelSpider

settings = get_project_settings()
process = CrawlerProcess(settings=settings)
process.crawl(NovelSpider)

process.start()
