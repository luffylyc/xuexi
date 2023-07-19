package com.xuecheng.content.feignclient;

import com.xuecheng.content.feignclient.modle.CourseIndex;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author cmy
 * @version 1.0
 * @description TODO
 * @date 2023/3/7 21:03
 */
@Slf4j
@Component
public class SearchServiceClientFallbackFactory implements FallbackFactory<SearchServiceClient> {

    @Override
    public SearchServiceClient create(Throwable throwable) {
        return new SearchServiceClient() {
            @Override
            public Boolean add(CourseIndex courseIndex) {
                throwable.printStackTrace();
                log.debug("熔断异常",throwable.getMessage());
                return false;
            }
        };
    }
}
