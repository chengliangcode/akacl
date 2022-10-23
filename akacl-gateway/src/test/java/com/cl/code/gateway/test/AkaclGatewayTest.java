package com.cl.code.gateway.test;

import com.cl.cocde.gateway.AkaclGatewayApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

/**
 * @author chengliang
 * @since 2022/10/23 9:42
 */
@SpringBootTest(classes = AkaclGatewayApplication.class)
public class AkaclGatewayTest {

    @Test
    public void test() {
        Flux<Integer> map = Flux.just(100, 200).map(s -> {
            System.out.println(s);
            return s;
        });

        map.subscribe();

    }

}
