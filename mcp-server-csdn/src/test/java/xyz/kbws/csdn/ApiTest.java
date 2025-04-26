package xyz.kbws.csdn;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;
import xyz.kbws.csdn.domain.model.ArticleFunctionRequest;
import xyz.kbws.csdn.domain.model.ArticleFunctionResponse;
import xyz.kbws.csdn.domain.service.CSDNArticleService;
import xyz.kbws.csdn.infrastructure.gateway.ICSDNService;
import xyz.kbws.csdn.infrastructure.gateway.dto.ArticleRequestDTO;
import xyz.kbws.csdn.infrastructure.gateway.dto.ArticleResponseDTO;
import xyz.kbws.csdn.types.utils.MarkdownConverter;
import xyz.kbws.csdn.types.utils.SignatureUtils;

import java.io.IOException;
import java.util.Collections;

/**
 * @author kbws
 * @date 2025/4/26
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {
    private final Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Autowired
    private ICSDNService csdnService;

    @Autowired
    private CSDNArticleService csdnArticleService;

    @Test
    public void test_saveArticle() throws IOException {
        // 1. 构建请求对象
        ArticleRequestDTO request = new ArticleRequestDTO();
        request.setTitle("测试文章 1111");
        request.setDescription("123123123123213123");
        request.setContent("<p>123123123123213123</p>\n");
        request.setTags("测试");
        request.setCategories("Java面试");
        request.setType("original");
        request.setStatus(0);
        request.setReadType("public");
        request.setOriginal_link("");
        request.setAuthorized_status(false);
        request.setSource("pc_postedit");
        request.setNot_auto_saved("1");
        request.setCover_images(Collections.emptyList());
        request.setCover_type(1);
        request.setIs_new(1);
        request.setVote_id(0);
        request.setResource_id("");
        request.setSync_git_code(0);
        request.setPubStatus("draft");

        // 3. 调用接口
        String cookie = "uuid_tt_dd=10_37481455750-1729751714749-504789; fid=20_97353541075-1729751715758-294666; UN=qq_62566185; c_dl_um=-; ssxmod_itna=YqGx2CD=iti=6xBab8DyDI2GoqLP0KiDCKwiDNxBuAo4iNDnD8x7YDv+ITKo+3KnG0Ydfe+gemxfFQ8OdqduQbPKf/mDAoDhx7QDox0=DnxAQDj1R5GGUxBYDQxAYDGDDpcDGdVgRD7hU9+LUj3kj6twtDmfj0DGjRDitoxivDyLNSR8DSDDfYxB=DxAf0D7Ul3LdDbqDuF6Q5EqDLmnnax=QDbrPyDPWDtdT2S4DHUTN/3TyYBA3slODT8GP=KOheYDqibY+QtGmb/2+PC0w4fY4jWXTemDdxD=; ssxmod_itna2=YqGx2CD=iti=6xBab8DyDI2GoqLP0KiDCKwiDx8d=xqGXIpPGaU0I6KC5IE5yLO79XkYSgfYXsFIgg7+8iVxciwhUAzk8nvCVon32G56FAoxHnBQd2NzP1oWYAB=qFst/UlKnDAidD+G8nxmOKCGXtjKne80PfkiPpnpxxAPh07ROYOddaRPIjW37I2R+ikeT=mtwjmoY=iAHXRDzjWcXUxuxp=U1EEc1p69OEoFGLZRCd1nwWcbqV20AX+8qhmKdZxoTcfbTgo9PMTvh2EOz4WnkGeSX1PWqnKM1UQj1eaS1A93Y27HWx=LgWC4oHbYVkiWRFdR=Fgr9uts2im+o+dtXrOuDzixFrfo70NihbWqWFxTHrWG8pHg1fQCc4p/dtAlwKG8QoiKD3BSYk0WCLhkMvuRYVD85=WIfhVDp=ebZj3HgFlmfzw5KB5Yn3XGLu0NHSwEmHAwjKxbs3AHgbGSbbdPD7QnDGcDG7eiDD==; tfstk=fQjmYciizQGCr-ukEz-b7EAzOpz8cxt6F1n96hdazQR7ChKADaAGQ6897OeXQ_XFtthOkOC-4Kpiuq8Z0U5l6OnOHS1tj_fCtcefk1dNZdp6Qf8Z0YoyQn81kCdvshX1t8FLvkBfhhtY9WEK4ErGWhOaXhoFRe2ebWFL2j6L8axNMKJo60XyNdmq0m5Za4RW3q-wgFlrzpRybC5wg4oyKK0Z_mRaETRWQhRwzKPJMIR14SKacrzXpIIknUzR3QrRIgvDoBWVmcoZ8KYDTtRzOy3VbFfDogEmOKbPKspc1kiX0FbV8nSgtoxPdaC2E1PnSQ7hcGYdmW0BgiO1tn7uamxwcibpRh4nhLIcUMTGcSmyi_6PJnsUNcfFe9IXPGVnaC_vd3JcrRuygFSPJ4uEP1i6URIr5VT2FLAplDeKBiqoonwuE2JDuL9q282o5oY2FLALE80UUEJW30C..; c_dl_prid=1729940610285_805792; c_dl_rid=1732784233297_469772; c_dl_fref=https://blog.csdn.net/u011418943; c_dl_fpage=/download/u011418943/10292537; _ga_JJBD2VG1H7=GS1.1.1735717971.5.1.1735718001.30.0.0; __gads=ID=c049149542f2f9bb:T=1729751984:RT=1739977742:S=ALNI_MbRee4m0F3oEH2FdzN8DBvdZoayQg; __gpi=UID=00000f519bf181af:T=1729751984:RT=1739977742:S=ALNI_MYfcEWPKeu_uJ3Ai2GVgfGHIhh8ow; FCNEC=%5B%5B%22AKsRol9FUVYeTcxbbXyoxJdv5rVAkYE_m04f5xZuBmmfL5Sw76J27I4yc7GRe4YFSzFnXCbtjBd3GOsQ9xlMiQPQllQVR5ypmg9DEpZe3EwjsJF6Ct6OJY67l-iL6z4vkcd8ZxhC3KpBff4onWRzamLjYB40wk_msw%3D%3D%22%5D%5D; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1744870581,1744905863,1744948353,1745148516; _ga=GA1.2.2057609489.1729751718; _clck=12dq8y1%7C2%7Cfv8%7C0%7C1758; _ga_7W1N0GEY1P=GS1.1.1745148516.342.1.1745148563.13.0.0; c_adb=1; c_first_ref=www.google.com; c_segment=5; dc_sid=bb9ec563dea150944ae4ecd9134df548; creative_btn_mp=3; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1745637900950%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E7%A9%BA%E7%99%BD%E6%97%A0%E4%B8%8A%22%2C%22blog-threeH-dialog-expa%22%3A1745564276271%7D; dc_session_id=10_1745649305226.244313; c_first_page=https%3A//www.csdn.net/; c_dsid=11_1745649305584.813010; c_ab_test=1; SESSION=63dfae3b-29e7-485a-9a52-20b6a8a92b9a; UserName=qq_62566185; UserInfo=98fc5a658c9649f6aaf7c9ede4d42395; UserToken=98fc5a658c9649f6aaf7c9ede4d42395; UserNick=%E7%A9%BA%E7%99%BD%E6%97%A0%E4%B8%8A; AU=40A; BT=1745649322721; p_uid=U010000; csdn_newcert_qq_62566185=1; c_page_id=default; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20230921102607.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A1%2C%22type%22%3A2%2C%22oldUser%22%3Atrue%2C%22useSeven%22%3Afalse%2C%22oldFullVersion%22%3Atrue%2C%22userName%22%3A%22qq_62566185%22%7D; c_pref=https%3A//blog.csdn.net/; c_ref=https%3A//mpbeta.csdn.net/mp_blog/manage/article; log_Id_pv=29; log_Id_view=446; dc_tos=svbdl4; log_Id_click=25";
        Call<ArticleResponseDTO> call = csdnService.saveArticle(request, cookie);
        Response<ArticleResponseDTO> response = call.execute();

        System.out.println("响应内容：" + JSON.toJSONString(response.body()));
        System.out.println("响应码：" + response.code());
        System.out.println("\r\n测试结果" + JSON.toJSONString(response));

        // 4. 验证结果
        if (response.isSuccessful()) {
            ArticleResponseDTO articleResponseDTO = response.body();
            log.info("发布文章成功 {}", articleResponseDTO);
        }
    }

    @Test
    public void test_md2html() {
        System.out.println(MarkdownConverter.convertToHtml("**关于DDD是什么，在维基百科有一个明确的定义。\"Domain-driven design (DDD) is a major software design approach.\" 也就是说DDD是一种主要的软件设计方法。而软件设计涵盖了；范式、模型、框架、方法论。**\n" +
                "\n" +
                "- 范式（paradigm）指的是一种编程思想。\n" +
                "- 模型（model）指的是对现实世界或者问题的抽象描述。\n" +
                "- 框架（framework）指的是提供了一系列通用功能和结构的软件工具。\n" +
                "- 方法论（methodology）指的是一种系统的、有组织的解决问题的方法。\n" +
                "\n" +
                "所以，DDD不只是只有指导思想，伴随的DDD的还包括框架结构分层。但说到底，这些仍然是理论讨论。在没有一个DDD落地项目物参考下，其实大部分码农是没法完成DDD开发的。所以小傅哥今年花费了5个月假期/周末的时间，完成的《DDD简明开发教程》，帮助大家落地DDD编码。"));
    }

    @Test
    public void test_domain_saveArticle() throws IOException {
        String json = "{\"content\":\"<h2>场景：</h2>\\n<p>在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。</p>\\n<p><strong>面试官</strong>：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？</p>\\n<p><strong>程序员小张</strong>：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？</p>\\n<p><strong>面试官</strong>：嗯，第二个问题，请说说HashMap的工作原理。</p>\\n<p><strong>程序员小张</strong>：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……</p>\\n<p><strong>面试官</strong>：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？</p>\\n<p><strong>程序员小张</strong>：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……</p>\\n<p><strong>面试官</strong>：好，今天的问题就问到这里。回去等通知吧。</p>\\n<h2>答案解析：</h2>\\n<ol>\\n<li>\\n<p><strong>JVM内存管理</strong>：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。</p>\\n</li>\\n<li>\\n<p><strong>HashMap原理</strong>：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。</p>\\n</li>\\n<li>\\n<p><strong>Spring与SpringBoot区别</strong>：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。</p>\\n</li>\\n</ol>\\n\",\"cover_images\":[],\"cover_type\":0,\"description\":\"在互联网大厂的面试中，严肃的面试官与搞笑的程序员上演了一场精彩的对话。面试官提出Java核心知识、HashMap、Spring等问题，程序员则用幽默的方式作答。本文不仅展现了轻松的面试氛围，还附上了详细的技术问题答案解析，帮助读者更好地理解相关知识。\",\"is_new\":1,\"level\":\"0\",\"markdowncontent\":\"## 场景：\\n\\n在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。\\n\\n**面试官**：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？\\n\\n**程序员小张**：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？\\n\\n**面试官**：嗯，第二个问题，请说说HashMap的工作原理。\\n\\n**程序员小张**：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……\\n\\n**面试官**：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？\\n\\n**程序员小张**：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……\\n\\n**面试官**：好，今天的问题就问到这里。回去等通知吧。\\n\\n## 答案解析：\\n\\n1. **JVM内存管理**：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。\\n\\n2. **HashMap原理**：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。\\n\\n3. **Spring与SpringBoot区别**：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。\",\"not_auto_saved\":\"0\",\"pubStatus\":\"draft\",\"readType\":\"public\",\"resource_id\":\"\",\"resource_url\":\"\",\"source\":\"pc_mdeditor\",\"status\":0,\"sync_git_code\":0,\"tags\":\"Java,面试,互联网,程序员,Spring,SpringBoot,HashMap,JVM\",\"title\":\"互联网大厂Java面试：严肃面试官与搞笑程序员的对决\",\"vote_id\":0}";
        ArticleFunctionRequest request = JSON.parseObject(json, ArticleFunctionRequest.class);
        ArticleFunctionResponse response = csdnArticleService.saveArticle(request);
        log.info("测试结果:{}", JSON.toJSONString(response));
    }

}
