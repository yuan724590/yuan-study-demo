package yuan.study.demo.service.designPattern.visitor;

import lombok.Data;

@Data
public abstract class Birds {

    /**
     * 名字
     */
    private String name;

    /**
     * 品种
     */
    private String breed;

    /**
     * 性格
     */
    private String disposition;

    public abstract void accept(Visitor visitor);
}
