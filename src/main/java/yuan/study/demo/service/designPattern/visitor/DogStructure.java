package yuan.study.demo.service.designPattern.visitor;

import java.util.ArrayList;
import java.util.List;

public class DogStructure {

    private List<DogBirds> dogStructureList = new ArrayList<>();

    public void accept(Visitor visitor){
        for (DogBirds dogBirds : dogStructureList){
            dogBirds.accept(visitor);
        }
    }

    public void add(DogBirds dogBirds){

        dogStructureList.add(dogBirds) ;
    }
}
