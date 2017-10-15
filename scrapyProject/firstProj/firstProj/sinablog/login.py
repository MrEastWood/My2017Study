import requests
import base64
import re
import urllib
import urllib.parse
import rsa
import json
import binascii
import time

class UserLogin:
    def userlogin(self,username,password):
        session = requests.session()
        url_prelogin = 'http://login.sina.com.cn/sso/prelogin.php?entry=weibo&' \
                       'callback=sinaSSOController.preloginCallBack&su=&rsakt=mod&' \
                       'client=ssologin.js(v1.4.5)&_=1364875106625'
        url_login = 'http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.5)'

        #预登陆，获取 servertime,nonce,pubkey,rsakv
        resp = session.get(url_prelogin)
        json_data = re.findall(r'(?<=\().*(?=\))',resp.text)[0]
        print(json_data)

        data = json.loads(json_data)

        servertime = data['servertime']
        nonce = data['nonce']
        pubkey = data['pubkey']
        rsakv = data['rsakv']

        #用户名加密
        print(urllib.parse.quote(username))
        su = base64.b64encode(username.encode(encoding="utf-8"))

        #密码加密
        rsaPublickey = int(pubkey,16)
        key = rsa.PublicKey(rsaPublickey,65537)
        message = str(servertime) + '\t' + str(nonce) + '\n' + str(password)
        sp = binascii.b2a_hex(rsa.encrypt(message.encode(encoding='utf-8'),key))

        postdate = {
            'entity' : 'weibo',
            'gateway' : '1',
            'from' : '',
            'savestate' : '7',
            'qrcode_flag' : 'false',
            'useticket' : '1',
            'pagerefer' : '',
            'vsnf' : '1',
            'su' : su,
            'service' : 'miniblog',
            'servertime' : servertime,
            'nonce' : nonce,
            'pwencode' : 'rsa2',
            'rsakv' : rsakv,
            'sp' : sp,
            'sr' : '1920*1080',
            'encoding' : 'UTF-8',
            'prelt' : '53',
            'url' : 'http://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack',
            'returntype' : 'META'
        }

        resp = session.post(url_login,data=postdate)

        print('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@')
        cookies = resp.cookies
        print(cookies)

        respText = resp.content.decode(encoding="GBK")
        respText = respText.replace('%3A', ':').replace('%2F', '/').replace('%3F', '?').replace('%3D', '_').replace('%26', '&')
        print(respText)

        login_url = re.findall(r'http://weibo.*&retcode=0',respText)
        print(login_url)

        respo = session.get(login_url[0], cookies=cookies)
        cookies = respo.cookies
        print(cookies)

        print(respo.text)
        uid = re.findall(r'"uniqueid":"(\d+)",',respo.text)[0]
        finalurl = "http://weibo.com/u/" + uid
        respo = session.get(finalurl, cookies=cookies)
        cookies = respo.cookies
        print(cookies)

        print(respo.text)
        time.sleep(3)

        #关注
        timeStr = str(int(time.time() * 1000))
        url_follow = 'https://weibo.com/aj/f/followed?ajwvr=6&__rnd=' + timeStr
        print(url_follow)

        postdata = {
            'uid': '5456956479',
            'objectid': '',
            'f': '1',
            'extra': '',
            'refer_sort': '',
            'refer_flag': '1005050001_',
            'location': 'page_100505_home',
            'oid': '5456956479',
            'wforce': '1',
            'nogroup': 'false',
            'fnick': '周大静_',
            'refer_lflag': '',
            'refer_from': 'profile_headerv6',
            '_t': '0'
        }
        print('###############################################################')
        resp = session.post(url_follow, data=postdata, cookies=cookies)
        print(resp.text)



