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

    byte[] compress(byte[] bytes);


    byte[] decompress(byte[] bytes);
}

