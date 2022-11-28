package com.zpskt.repogenerate;

import com.zpskt.repogenerate.config.Config;
import com.zpskt.repogenerate.execute.OriginGenerator;
import lombok.extern.slf4j.Slf4j;

/**
 * @descriptions: 主类
 * @author: 22035483 zhangpeng
 * @date: 2022/11/28 11:06
 * @version: 1.0
 */
@Slf4j
public class RepoGenerateMain {
    public static void main(String[] args) {
        Config config =new Config("zhangpeng","com.haier.example",
                        "com.haier",null,"C:\\Users\\22035483\\Desktop\\GitDoc\\RepoGenerate",
                "jdbc:mysql://127.0.0.1:3306/develop_forum","root","zhangpeng","all");

        OriginGenerator originGenerator = new OriginGenerator(config);
        originGenerator.generateCode();
    }
}
