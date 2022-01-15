package yuan.study.demo.service.designPattern.flyweight;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Gymnasium implements Architecture {

    /**
     * 体育馆的名字
     */
    private String name;

    Gymnasium(String name){
        this.name = name;
    }

    @Override
    public void getUsage() {
        System.out.println("这个体育馆名字叫做" + name + ", 对象地址:" + this);
    }
}