package com.cl.code;

import com.cl.code.common.datasource.log.DaoLogImpl;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chengliang
 * @since 2022/11/9 0:30
 */
@SpringBootApplication
public class AkaclSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkaclSystemApplication.class, args);
    }

}
