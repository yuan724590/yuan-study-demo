package yuan.study.demo.service.designPattern.visitor;

import lombok.Data;

@Data
public class DogBirds extends Birds {

    /**
     * 价格
     */
    private String price;

    @Override
    public void accept(Visitor visitor) {
        visitor.payPrice(this);
    }
}
