/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: DemoTransfrom
 * Author: lyl
 * Date: 2021/10/16 14:51
 * Description: 用一句话描述下
 */
package com.stark.plugins

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import org.gradle.api.Project

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.com.stark.plugins
 * @ClassName: DemoTransfrom
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/10/16 14:51
 */
public class DemoTransform extends Transform {

    private Project mProject;

    public DemoTransform(Project project) {
        mProject = project;
    }

    // 对应的task名称
    @Override
    public String getName() {
        return "addToast";
    }

    // 要对那些类型的结果进⾏转换(是字节码还是资源⽂件？)
    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    // 适⽤范围包括什么(整个 project 还是别的什么？)
    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    // 具体的「转换」过程
    @Override
    public void transform(TransformInvocation transformInvocation) {
        println("--------------Transform分界线-------------")
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();
        Collection<TransformInput> inputs = transformInvocation.getInputs();
        inputs.each {input ->
            // 包含我们手写的 Class 类及 R.class、BuildConfig.class 等
            input.directoryInputs.each { directoryInput ->
                String path = directoryInput.file.absolutePath
                println("[InjectTransform] Begin to inject: $path")

                // 执行注入逻辑
                InjectByJavassit.inject(path, mProject)

                // 获取输出目录
                def dest = transformInvocation.outputProvider.getContentLocation(directoryInput.name,
                        directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
                println("[InjectTransform] Directory output dest: $dest.absolutePath")

                // 将input的目录复制到output指定目录
                FileUtils.copyDirectory(directoryInput.file, dest)
            }

            // jar文件，如第三方依赖
            input.jarInputs.each { jarInput ->
                def dest = transformInvocation.outputProvider.getContentLocation(jarInput.name,
                        jarInput.contentTypes, jarInput.scopes, Format.JAR)
                FileUtils.copyFile(jarInput.file, dest)
            }
        }
    }
}
