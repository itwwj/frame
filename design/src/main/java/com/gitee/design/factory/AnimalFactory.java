package com.gitee.design.factory;

/**
 * @author jie
 */
public class AnimalFactory  {


    public static  <T extends Animal> T createAnimal(Class<T> tClass) {
        Animal animal = null;
        try {
            animal = (T) Class.forName(tClass.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) animal;
    }
}
