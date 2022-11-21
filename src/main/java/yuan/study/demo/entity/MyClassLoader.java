package yuan.study.demo.entity;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@AllArgsConstructor
public class MyClassLoader extends ClassLoader{

    private String classpath;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        try{
            byte[] bytes = loadByte(name);
            return defineClass(name, bytes, 0, bytes.length);
        }catch (Exception e){
            throw new ClassNotFoundException();
        }
    }

    private byte[] loadByte(String className) throws IOException{
        FileInputStream fileInputStream = new FileInputStream(classpath + File.separator
                + className.replace(".", File.separator).concat(".class"));
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        fileInputStream.close();
        return bytes;
    }
}