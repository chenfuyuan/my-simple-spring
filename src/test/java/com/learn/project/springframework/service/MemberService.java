package com.learn.project.springframework.service;

import java.util.Random;

/**
 * MemberService
 *
 * @author chenfuyuan
 * @date 2023/2/16 17:24
 */
public class MemberService implements IMemberService{
    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "小傅哥，100001，深圳";
    }
}
