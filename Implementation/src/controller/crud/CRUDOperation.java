package controller.crud;

import controller.mainOperation;
import model.*;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CRUDOperation extends mainOperation {

    /**
     *
     * Check type of object and returns the list of same type.
     *
     * @param obj
     * @param <T>
     * @return List<T>
     *         T of List<T> == T of T obj
     */
    private static <T> List<T> typeCheck(T obj) {
        List<T> list = null;

        if (obj instanceof Professional)
            list = (LinkedList<T>) listPro;
        if (obj instanceof Member)
            list = (LinkedList<T>) listMember;
        if (obj instanceof Service)
            list = (LinkedList<T>) listService;

        return list;
    }

    /**
     *
     * Add object T to its respective list T.
     *
     * @param obj
     * @param <T>
     */
    public static <T extends Comparable<T>> void add(T obj) {
        List<T> list = typeCheck(obj);
        list.add(obj);
    }

    /**
     *
     * Find object T from its respective list T.
     *
     * @param obj
     * @param <T>
     * @return int
     *         0 -> Ok
     *        -1 -> Error
     */
    public static <T extends Comparable<T>> int find(T obj) {
        List<T> list = typeCheck(obj);
        assert list != null;
        Collections.sort(list);
        return Collections.binarySearch(list, obj);
    }

    /**
     *
     * Update object T from its respective list T.
     *
     * @param obj
     * @param option
     * @param newValue
     * @param <T>
     * @return int
     *         0 -> Ok
     *        -1 -> Error
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    public static <T extends Comparable<T>> int update(T obj, int option, String newValue) throws ClassNotFoundException, IllegalAccessException {
        String className = obj.getClass().getSuperclass().getName();
        
        if (className.equals("java.lang.Object")) {
            className = obj.getClass().getName();
        }
        Class<?> myClass = Class.forName(className);

        int index = find(obj);
        
        if(index < 0) {
        	return -1;
        }

        List<T> list = typeCheck(obj);
        T item = list.get(index);

        int counter = 0;
        for(Field field : myClass.getDeclaredFields()) {
            if (!field.getName().equals("serialVersionUID") &&
                !field.getName().equals("id")
            ) {
                if (counter == option) {
                    field.setAccessible(true);
                    if (field.getName().equals("maxCap")) {
                    	int newValueInt = Integer.parseInt(newValue); 
                    	field.set(item, newValueInt);
                    }
                    
                    if (field.getName().equals("cost")) {
                    	double newValueDouble = Double.valueOf(newValue.toString());
                    	field.set(item, newValueDouble);
                    }  
                    
                    if (!field.getName().equals("maxCap") &&
                            !field.getName().equals("cost")
                        ) {
                    	field.set(item, newValue);
                    }
                    
                    return 0;
                }

                counter++;
            }
        }

        return -1;
    }

    /**
     *
     * Deletes object T from its respective list T.
     *
     * @param obj
     * @param <T>
     * @return int
     *         0 -> Ok
     *        -1 -> Error
     */
    public static <T extends Comparable<T>> int delete(T obj) {
        List<T> list = typeCheck(obj);
        int index = find(obj);

        if (index >= 0) {
            list.remove(index);
            return 0;
        }
        else
            return -1;
    }
}
