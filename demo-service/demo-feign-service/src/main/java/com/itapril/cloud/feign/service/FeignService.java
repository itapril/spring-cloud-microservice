package com.itapril.cloud.feign.service;

import com.itapril.cloud.feign.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by itapril on 2018/5/28 17:01.
 */
@FeignClient(value = "demo-provider-service")
public interface FeignService {
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    String helloWorld(@RequestParam(value = "name") String name,@RequestParam(value = "port") String port);

    // 该请求不会成功，只要参数是复杂对象，即使指定了是GET方法，feign依然会以POST方法进行发送请求。
    @RequestMapping(value = "getUser", method = RequestMethod.GET)
    User getUser(@RequestBody User user);

    @RequestMapping(value = "postUser", method = RequestMethod.POST)
    User postUser(@RequestBody User user);

    /**
     *provider 提供出来的RESTFul是
     *
     * @GetMapping("simple/{id}")
       public String getSimple(@PathVariable Long id)

     如果在Feign中使用的方式为
         @RequestMapping(value = "simple/{id}", method = RequestMethod.GET)
         String getSimple1(@PathVariable Long id);
     启动服务会报错：
         Caused by: java.lang.IllegalStateException: PathVariable annotation was empty on param 0.
     所以需要指定PathVariable中的value --> @PathVariable("id")
     *
     *
     */
    @RequestMapping(value = "simple/{id}", method = RequestMethod.GET)
    String getSimple1(@PathVariable("id") Long id);

    // 不能直接用@GetMapping 请求会报错
    @GetMapping("simple/{id}")
    String getSimple2(Long id);

}
