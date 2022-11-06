package com.cl.code.common.util;

/**
 * id生成器（雪花算法）
 *
 * @author chengliang
 * @since 2022/11/6 23:19
 */
public class IdGeneratorUtil {

    private static final SnowFlake snowFlake = new SnowFlake(1, 1);

    public static Long generateId() {
        return snowFlake.nextId();
    }

    public static Long generateId(long dataCenterId, long machineId) {
        return new SnowFlake(dataCenterId, machineId).nextId();
    }

}
