package yuan.study.demo.service.designPattern.facade;

import lombok.Data;

@Data
public class AnimalFacade {

    /**
     * 动物的外观
     */
    public void getAnimalFacade(){
        new Cat().say();
        new Dog().say();
    }
}
