package com.kris.compress;

import com.kris.extension.SPI;

/**
 * @Program: kris-rpc
 * @Description: 压缩接口类
 * @Author: kris
 * @Create: 2025-03-16 15:57
 **/
@SPI
public interface Compress {

    /**
     * 压缩
     * @param bytes 数据
     * @return 返回压缩后的
     */
    byte[] compress(byte[] bytes);

    /**
     * 解压缩
     * @param bytes 数据
     * @return 返回压缩后的
     */
    byte[] decompress(byte[] bytes);
}

