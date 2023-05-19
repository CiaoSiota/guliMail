package com.java.gulimall.thirdparty;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class GulimallThirdPartyApplicationTests {

    @Test
    void contextLoads() throws FileNotFoundException {

        String endpoint = "oss-cn-beijing.aliyuncs.com";

        String accessKeyId = "LTAI5t5xrGcSzafMG6AwD26X";
        String accessKeySecret = "Rr11hZCaf1chEcqlpN5t3pYT8U1FWJ";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "siota";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        InputStream inputStream = new FileInputStream("C:\\Users\\ASUS\\Desktop\\1683699189000.png");

        ossClient.putObject(bucketName, "t3 .jpg", inputStream);

        ossClient.shutdown();

    }

}
