package yuan.study.demo.service.designPattern.proxy;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuyCarProxy implements BuyCar{

    private Customer customer;

    @Override
    public void buyCar(int cash) {
        //为客户买车
        customer.buyCar(cash);
    }

    @Override
    public String buyAHundredCars(int cash){
        return cash + "元买不起算了吧";
    }
}