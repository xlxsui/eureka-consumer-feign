package top.xlxs.eurekaconsumerfeign.service;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 微服务的项目名称
 */
@Service
@FeignClient(value = "eureka-provider", configuration = FileFeignService.MultipartSupportConfig.class)
public interface FileFeignService {

    /**
     * 调用"eureka-provider"服务下的"provider/{name}/hi"接口
     * 此位置的mapping与controller上的mapping是共享全局唯一的，
     * 如果相同会报错ambiguous mapping
     *
     * @param name 名字路径参数
     * @return 问候信息
     */
    @RequestMapping("/provider/hi/{name}")
    String getHiFeign(@PathVariable(value = "name") String name);

    @PostMapping(value = "/provider/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String handleFileUpload(@RequestPart(value = "file") MultipartFile file);

    @Configuration
    class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }
}
