package top.xlxs.eurekaconsumerfeign.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xlxs.eurekaconsumerfeign.service.FileFeignService;

@RestController
@RequestMapping(value = "/consumer-feign", produces = "application/json; charset=utf-8")
public class ConsumerFeignController {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerFeignController.class);

    @Autowired
    private FileFeignService fileFeignService;

    @RequestMapping("/getHiFeign/{name}")
    public String getHiFeign(@PathVariable String name) {
        logger.info("************getHiFeign name:{}", name);
        // 调用微服务
        String result = fileFeignService.getHiFeign(name);
        logger.info("************getHiFeign msg:{}", result);
        return result;
    }
}
