package top.xlxs.eurekaconsumerfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 微服务的项目名称
 */
@Service
@FeignClient("eureka-provider")
public interface HiFeignService {

    /**
     * 调用"eureka-provider"服务下的"provider/{name}/hi"接口
     * 此位置的mapping与controller上的mapping是共享全局唯一的，
     * 如果相同会报错ambiguous mapping
     *
     * @param name 名字路径参数
     * @return 问候信息
     */
    @RequestMapping("provider/hi/{name}")
    String getHiFeign(@PathVariable(value = "name") String name);
}
