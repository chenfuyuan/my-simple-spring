package com.learn.project.springframework.service;

import com.learn.project.springframework.stereotype.Component;

/**
 * ComponentServiceImpl
 *
 * @author chenfuyuan
 * @date 2023/2/22 13:56
 */
@Component("componentService")
public class ComponentServiceImpl implements ComponentService{
    @Override
    public void invoke() {
        System.out.println(this.getClass().getSimpleName() + ".invoke");
    }
}
