package yuan.study.demo.service.designPattern.interpreter;

import lombok.Data;

/**
 * 包含解释器之外的一些信息 
 */
@Data
public class Context {

    /**
    * 语言类型
    */
    private String data;
}