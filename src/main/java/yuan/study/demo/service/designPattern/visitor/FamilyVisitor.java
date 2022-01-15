package yuan.study.demo.service.designPattern.visitor;

public class FamilyVisitor implements Visitor {

    @Override
    public void payPrice(DogBirds dogBirds) {
        System.out.println(String.format("家庭单位，购买[%s]付出的价格为[%s]", dogBirds.getName(), dogBirds.getPrice()));
    }
}