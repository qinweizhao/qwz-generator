package com.qinweizhao.generator.exception;

/**
 * 模板渲染失败异常
 *
 * @author hccake
 */
public class TemplateRenderException extends RuntimeException {

    public TemplateRenderException(Exception e) {
        super(e);
    }

}
