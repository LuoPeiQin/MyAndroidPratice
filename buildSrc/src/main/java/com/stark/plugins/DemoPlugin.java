/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: DemoPlugin
 * Author: lyl
 * Date: 2021/10/16 14:19
 * Description: 用一句话描述下
 */
package com.stark.plugins;

import com.android.build.gradle.BaseExtension;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.plugins
 * @ClassName: DemoPlugin
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/10/16 14:19
 */
public class DemoPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        // Transform执行测试
        BaseExtension baseExtension = project.getExtensions().getByType(BaseExtension.class);
        baseExtension.registerTransform(new DemoTransform());

        // 扩展执行测试
//        System.out.println("apply 执行1");
//        final DemoExtension extension = project.getExtensions().create("inputName", DemoExtension.class);
//        System.out.println("apply 执行2");
//        project.afterEvaluate(new Action<Project>() {
//            @Override
//            public void execute(Project project) {
//                System.out.println("apply 执行3");
//                System.out.println("Hello " + extension.myName);
//            }
//        });
//        System.out.println("apply 执行4");

    }
}
