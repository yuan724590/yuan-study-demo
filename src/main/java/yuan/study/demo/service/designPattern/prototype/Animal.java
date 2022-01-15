package yuan.study.demo.service.designPattern.prototype;

import lombok.Data;

@Data
public abstract class Animal implements Cloneable{

    public abstract void say();

    public Object clone(){
        Object clone = null;
        try {
            clone = super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clone;
    }
}
