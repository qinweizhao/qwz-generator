package com.qinweizhao.generator.engine;

import com.qinweizhao.generator.exception.TemplateRenderException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Freemarker 的模板引擎
 *
 * @author hccake
 */
@Component
public class FreemarkerTemplateEngine implements TemplateEngine {

    private final Configuration configuration;

    public FreemarkerTemplateEngine() {
        this.configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
    }

    @Override
    public TemplateEngineTypeEnum type() {
        return TemplateEngineTypeEnum.FREEMARKER;
    }

    @SneakyThrows(TemplateRenderException.class)
    @Override
    public String render(String templateContent, Map<String, Object> context) {
        try {
            Template template = new Template("templateName", templateContent, configuration);
            try (StringWriter sw = new StringWriter()) {
                template.process(context, sw);
                return sw.toString();
            }
        } catch (Exception ex) {
            throw new TemplateRenderException(ex);
        }
    }

}
