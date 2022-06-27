/**
 * Copyright (C), 2007-2022, 未来穿戴有限公司
 * FileName: Builder_46
 * Author: lpq
 * Date: 2022/6/24 11:06
 * Description: 用一句话描述下
 */
package com.stark.javalib.designpatterns;

import java.security.InvalidParameterException;

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib.designpatterns
 * @ClassName: Builder_46
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2022/6/24 11:06
 */
public class Builder_46 {
    private boolean isRef;
    private Class type;
    private Object arg;

    private Builder_46(Builder builder) {
        this.isRef = builder.isRef;
        this.type = builder.type;
        this.arg = builder.arg;
    }

    public boolean isRef() {
        return isRef;
    }

    public Class getType() {
        return type;
    }

    public Object getArg() {
        return arg;
    }

    public static class Builder {

        private boolean isRef;
        private Class type;
        private Object arg;

        public Builder_46 build() {
            if (isRef) {
                if (type == null || arg == null) {
                    throw new InvalidParameterException("type == null || arg == null");
                }
            }
            return new Builder_46(this);
        }

        public Builder setRef(boolean ref) {
            this.isRef = ref;
            return this;
        }

        public Builder setType(Class type) {
            this.type = type;
            return this;
        }

        public Builder setArg(Object arg) {
            this.arg = arg;
            return this;
        }
    }

}
