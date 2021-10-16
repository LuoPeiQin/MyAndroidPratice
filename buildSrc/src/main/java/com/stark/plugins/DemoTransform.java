/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: DemoTransfrom
 * Author: lyl
 * Date: 2021/10/16 14:51
 * Description: 用一句话描述下
 */
package com.stark.plugins;

import com.android.build.api.transform.Format;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.android.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.plugins
 * @ClassName: DemoTransfrom
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/10/16 14:51
 */
public class DemoTransform extends Transform {
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
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();
        Collection<TransformInput> inputs = transformInvocation.getInputs();
        inputs.forEach(it -> {
            it.getDirectoryInputs().forEach(directoryInput -> {
                File dest = outputProvider.getContentLocation(directoryInput.getName(),
                        directoryInput.getContentTypes(), directoryInput.getScopes(), Format.DIRECTORY);
                System.out.println("Dir: " + directoryInput.getFile());
                try {
                    FileUtils.copyDirectory(directoryInput.getFile(), dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            it.getJarInputs().forEach(jarInput -> {
                File dest = outputProvider.getContentLocation(jarInput.getName(),
                        jarInput.getContentTypes(), jarInput.getScopes(), Format.JAR);
                System.out.println("Jar: " + jarInput.getFile());
                try {
                    FileUtils.copyFile(jarInput.getFile(), dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}
