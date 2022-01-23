package yuan.study.demo.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.common.Constants;
import yuan.study.demo.entity.Instance;
import yuan.study.demo.service.DesignPatternService;
import yuan.study.demo.service.designPattern.SingletonPatternService;
import yuan.study.demo.service.designPattern.abstractFactory.AbstractFactory;
import yuan.study.demo.service.designPattern.abstractFactory.Apple;
import yuan.study.demo.service.designPattern.abstractFactory.FactoryProducer;
import yuan.study.demo.service.designPattern.abstractFactory.Huawei;
import yuan.study.demo.service.designPattern.adapter.Adapter;
import yuan.study.demo.service.designPattern.adapter.Target;
import yuan.study.demo.service.designPattern.adapter.TargetImpl;
import yuan.study.demo.service.designPattern.bridge.*;
import yuan.study.demo.service.designPattern.builder.AnotherWorker;
import yuan.study.demo.service.designPattern.builder.Director;
import yuan.study.demo.service.designPattern.builder.Worker;
import yuan.study.demo.service.designPattern.chainOfResponsibility.Handler1;
import yuan.study.demo.service.designPattern.chainOfResponsibility.Handler2;
import yuan.study.demo.service.designPattern.chainOfResponsibility.PipelineDemo;
import yuan.study.demo.service.designPattern.command.*;
import yuan.study.demo.service.designPattern.component.Component;
import yuan.study.demo.service.designPattern.component.Composite;
import yuan.study.demo.service.designPattern.component.Leaf;
import yuan.study.demo.service.designPattern.decorator.Benz;
import yuan.study.demo.service.designPattern.decorator.Car;
import yuan.study.demo.service.designPattern.decorator.CarWrapper;
import yuan.study.demo.service.designPattern.facade.AnimalFacade;
import yuan.study.demo.service.designPattern.flyweight.Architecture;
import yuan.study.demo.service.designPattern.flyweight.FlyweightFactory;
import yuan.study.demo.service.designPattern.interpreter.Context;
import yuan.study.demo.service.designPattern.interpreter.XmlDomInterpreter;
import yuan.study.demo.service.designPattern.interpreter.XmlSaxInterpreter;
import yuan.study.demo.service.designPattern.iterator.Aggregate;
import yuan.study.demo.service.designPattern.iterator.ConcreteAggregate;
import yuan.study.demo.service.designPattern.iterator.Iterator;
import yuan.study.demo.service.designPattern.mediator.ConcreteColleague;
import yuan.study.demo.service.designPattern.mediator.ConcreteMediator;
import yuan.study.demo.service.designPattern.mediator.Leader;
import yuan.study.demo.service.designPattern.memento.Original;
import yuan.study.demo.service.designPattern.memento.Storage;
import yuan.study.demo.service.designPattern.observer.WeatherObserver;
import yuan.study.demo.service.designPattern.observer.WeatherData;
import yuan.study.demo.service.designPattern.prototype.Animal;
import yuan.study.demo.service.designPattern.prototype.Cat;
import yuan.study.demo.service.designPattern.prototype.Dog;
import yuan.study.demo.service.designPattern.proxy.*;
import yuan.study.demo.service.designPattern.simpleFactory.CarFactory;
import yuan.study.demo.service.designPattern.simpleFactory.Product;
import yuan.study.demo.service.designPattern.state.StartState;
import yuan.study.demo.service.designPattern.state.StateContext;
import yuan.study.demo.service.designPattern.state.StopState;
import yuan.study.demo.service.designPattern.strategy.EncryptContext;
import yuan.study.demo.service.designPattern.strategy.MD5Strategy;
import yuan.study.demo.service.designPattern.strategy.SHA1Strategy;
import yuan.study.demo.service.designPattern.template.AbstractTemplate;
import yuan.study.demo.service.designPattern.template.ConcreteTemplate;
import yuan.study.demo.service.designPattern.template.ConcretesTemplate;
import yuan.study.demo.service.designPattern.visitor.DogBirds;
import yuan.study.demo.service.designPattern.visitor.DogStructure;
import yuan.study.demo.service.designPattern.visitor.FamilyVisitor;
import yuan.study.demo.service.designPattern.visitor.PoliceVisitor;

import java.lang.reflect.Proxy;

@Slf4j
@Service
public class DesignPatternServiceImpl implements DesignPatternService {


    @Override
    public String singleton(){
        //双重校验锁
        Instance instance = SingletonPatternService.getInstanceByDoubleLock();
        //懒汉模式
        Instance lazyManInstance = SingletonPatternService.getInstanceByLazyMan();
        //饿汉模式
        Instance hungryManInstance = SingletonPatternService.getInstanceByHungryMan();
        return "success";
    }

    @Override
    public String simpleFactory(){
        Product product = new Product();
        //生产轮胎
        CarFactory tyre = product.product(Constants.TYRE);
        tyre.ability();
        //生产发动机
        CarFactory engine = product.product(Constants.ENGINE);
        engine.ability();
        return "success";
    }

    @Override
    public String abstractFactory(){
        //获取华为工厂
        AbstractFactory huaweiFactory = FactoryProducer.getFactory(Constants.HUAWEI);
        if(huaweiFactory != null){
            //生产普通款华为手机
            Huawei huawei = huaweiFactory.getHuawei(Constants.ORDINARY);
            huawei.productPhone();
            //生产限量版华为手机
            huawei = huaweiFactory.getHuawei(Constants.LIMITED_EDITION);
            huawei.productPhone();
        }
        //获取苹果工厂
        AbstractFactory appleFactory = FactoryProducer.getFactory(Constants.APPLE);
        if(appleFactory != null){
            //生产普通款苹果手机
            Apple apple = appleFactory.getApple(Constants.ORDINARY);
            apple.productPhone();
            //生产限量版苹果手机
            apple = appleFactory.getApple(Constants.LIMITED_EDITION);
            apple.productPhone();
        }
        return "success";
    }

    @Override
    public String builder(){
        //盖房子流程模板
        Director director = new Director();
        //盖房子
        String result = director.build(new Worker());
        //看一下盖房子的效果
        System.out.println(result);

        //盖房子流程模板
        director = new Director();
        //盖房子
        result = director.build(new AnotherWorker());
        //看一下盖房子的效果
        System.out.println(result);

        return "success";
    }

    @Override
    public String prototype(){
        Animal animal = new Cat();
        animal.say();
        Animal cat = (Animal) animal.clone();
        cat.say();

        animal = new Dog();
        animal.say();
        Animal dog = (Animal) animal.clone();
        dog.say();
        return "success";
    }

    @Override
    public String adapter(){
        //原调用方法
        Target target = new TargetImpl();
        target.request();
        //适配器模式调用方法
        target = new Adapter();
        target.request();
        return "success";
    }

    @Override
    public String component(){
        Component component = new Composite("公司");

        component.add(new Leaf("总部"));

        Composite company = new Composite("分公司");
        company.add(new Leaf("人力资源部"));
        company.add(new Leaf("技术研发部"));
        component.add(company);

        Composite department = new Composite("公司俱乐部");
        Composite club = new Composite("研发部门俱乐部");
        club.add(new Leaf("足球俱乐部"));
        club.add(new Leaf("电竞俱乐部"));
        department.add(club);
        component.add(department);

        component.display(0);
        return "success";
    }

    @Override
    public String decorator(){
        //原本的类
        Car benz = new Benz();
        benz.run();
        benz.stop();
        //增强后的效果
        Car wrapperBenz = new CarWrapper(benz);
        wrapperBenz.run();
        wrapperBenz.stop();
        return "success";
    }

    @Override
    public String proxy(){
        //动态代理 - 通过jdk反射实现
        BuyCarHandler buyCarHandler = new BuyCarHandler(new Customer());
        //加上这句将会产生一个$Proxy0.class文件，这个文件即为动态生成的代理类文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //获取代理类实例BuyCar
        BuyCar buyCar = (BuyCar)(Proxy.newProxyInstance(BuyCar.class.getClassLoader(), new Class[] {BuyCar.class}, buyCarHandler));
        //通过代理类对象调用代理类方法，实际上会转到invoke方法调用
        buyCar.buyCar(10);

        //动态代理 - 通过Cglib实现
        BookDaoImpl proxy = (BookDaoImpl) CglibUtils.getProxy(BookDaoImpl.class);
        proxy.save();
        proxy.update();
        //原本的逻辑
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        bookDaoImpl.save();
        bookDaoImpl.update();


        //静态代理 - 消费者直接购买车
        Customer customer = new Customer();
        customer.buyCar(1600_0000);
        //消费者走中介买车
        BuyCarProxy buyCarProxy=new BuyCarProxy(customer);
        buyCarProxy.buyCar(2000_0000);
        return "success";
    }

    @Override
    public String flyweight(){
        String motionType = "普通体育馆";
        Architecture architecture;
        for(int i = 0; i < 3; i++){
            architecture = FlyweightFactory.getArchitectureInfo(motionType);
            architecture.getUsage();
        }
        return "success";
    }

    @Override
    public String facade(){
        AnimalFacade animalFacade = new AnimalFacade();
        animalFacade.getAnimalFacade();
        return "success";
    }

    @Override
    public String bridge(){

        Phone upRightPhone = new UpRightPhone(new Vivo());
        upRightPhone.open();
        upRightPhone.close();

        Phone foldedPhone = new FoldedPhone(new XiaoMi());
        foldedPhone.open();
        foldedPhone.close();

        return "success";
    }

    @Override
    public String template(){
        AbstractTemplate abstractTemplate = new ConcretesTemplate();
        //调用模板方法
        abstractTemplate.templateMethod();
        abstractTemplate = new ConcreteTemplate();
        abstractTemplate.templateMethod();
        return "success";
    }

    @Override
    public String interpreter(){
        Context context = new Context();
        context.setData(Constants.SAX);
        new XmlSaxInterpreter().interpret(context);
        context.setData(Constants.DOM);
        new XmlDomInterpreter().interpret(context);
        return "success";
    }

    @Override
    public String strategy(){
        EncryptContext context = new EncryptContext(new MD5Strategy());
        context.encrypt();
        context = new EncryptContext(new SHA1Strategy());
        context.encrypt();
        return "success";
    }

    @Override
    public String state(){
        StateContext context = new StateContext();
        StartState startState = new StartState();
        startState.doAction(context);
        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
        return "success";
    }

    @Override
    public String observer(){
        // 创建一个天气数据
        WeatherData weatherData = new WeatherData();
        // 注册到weatherData
        WeatherObserver weatherObserver = new WeatherObserver();
        weatherData.registerObserver(weatherObserver);
        weatherData.registerObserver(new WeatherObserver());
        weatherData.registerObserver(new WeatherObserver());
        weatherData.setData(10f, 11f, 12f);

        weatherData.removeObserver(weatherObserver);
        weatherData.setData(100f, 110f, 120f);
        return "success";
    }

    @Override
    public String memento(){
        // 创建原始类
        Original original = new Original("狗");
        // 创建备忘录
        Storage storage = new Storage(original.createMemento());

        System.out.println("现在的值是：" + original.getValue());

        // 进行修改
        original.setValue("可爱的狗");
        System.out.println("修改后的值是：" + original.getValue());

        // 恢复原始类的状态
        original.restoreMemento(storage.getMemento());
        System.out.println("恢复后的状态为：" + original.getValue());
        return "success";
    }

    @Override
    public String mediator(){
        ConcreteMediator mediator = new ConcreteMediator();

        ConcreteColleague colleague = new ConcreteColleague(mediator);
        Leader leader = new Leader(mediator);

        mediator.setColleague(colleague);
        mediator.setLeader(leader);

        colleague.send("代码开发不完了");
        leader.send("?");
        colleague.send("那我赶赶");
        return "success";
    }

    @Override
    public String command(){
        //调用者发起人
        Invoker invoker = new Invoker();
        //命令接收者处理人
        Receiver receiver = new Receiver();
        //下单命令
        Command command1 = new OrderTakeout(receiver);
        //送货命令
        Command command2 = new DeliverTakeout(receiver);
        //接受命令
        Command command3 = new ReceiveTakeout(receiver);
        invoker.setCommand(command1);
        invoker.setCommand(command2);
        invoker.setCommand(command3);
        //一次性递交所有暂存命令
        invoker.call();
        return "success";
    }

    @Override
    public String visitor(){
        //访问者为家庭
        FamilyVisitor familyVisitor = new FamilyVisitor();
        //访问者为警察局
        PoliceVisitor policeVisitor = new PoliceVisitor();

        DogStructure dogStructure = new DogStructure();
        DogBirds dogBirds = new DogBirds();
        dogBirds.setName("中华犬");
        dogBirds.setBreed("强壮");
        dogBirds.setDisposition("开朗");
        dogBirds.setPrice( "1000元");
        dogStructure.add(dogBirds) ;

        DogBirds dogBirds2 = new DogBirds();
        dogBirds2.setName("田园犬");
        dogBirds2.setBreed("忠顺");
        dogBirds2.setDisposition("温和");
        dogBirds2.setPrice("2880元");
        dogStructure.add(dogBirds2);

        dogStructure.accept(familyVisitor);
        dogStructure.accept(policeVisitor);
        dogBirds2.accept(policeVisitor);
        return "success";
    }

    @Override
    public String chainOfResponsibility(){
        PipelineDemo pipelineDemo = new PipelineDemo();
        pipelineDemo.addByLast(new Handler2());
        pipelineDemo.addByLast(new Handler1());

        //发起请求
        pipelineDemo.requestProcess("火车呜呜呜~~~");
        return "success";
    }

    @Override
    public String iterator(){
        Object[] objArray = {"One","Two","Three","Four","Five","Six","Seven","Eight"};
        //创建聚合对象
        Aggregate agg = new ConcreteAggregate(objArray);
        //循环输出聚合对象中的值
        Iterator iterator = agg.createIterator();
        while(!iterator.isDone()){
            System.out.println(iterator.currentItem());
            iterator.next();
        }
        iterator.first();
        return "success";
    }
}
