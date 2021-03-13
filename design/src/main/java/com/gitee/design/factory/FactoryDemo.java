package com.gitee.design.factory;

/**
 * @author jie
 */
public class FactoryDemo {
    public static void main(String[] args) {


        Animal animal = AnimalFactory.createAnimal(Dog.class);




        animal.say();
    }
}
