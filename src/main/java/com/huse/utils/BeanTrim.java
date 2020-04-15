package com.huse.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*清除空格*/
public class BeanTrim {

    public static void trim(Object bean) throws Exception {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field: fields){
            if (field.getType().toString().equals("java.lang.String")){
                Object value = getFieldValue(bean,field.getName());
                System.out.println(value);
                if (value == null)
                    continue;
                setFieldValue(bean, field.getName(), value.toString().trim());
            }
        }
    }
    private static Object getFieldValue(Object bean, String fieldName)
            throws Exception {
        StringBuffer result = new StringBuffer();
        String methodName = result.append("get")
                .append(fieldName.substring(0, 1).toUpperCase())
                .append(fieldName.substring(1)).toString();

        Object rObject = null;
        Method method = null;

        @SuppressWarnings("rawtypes")
        Class[] classArr = new Class[0];
        method = bean.getClass().getMethod(methodName, classArr);
        rObject = method.invoke(bean, new Object[0]);

        return rObject;
    }
    /**
     * 利用发射调用bean.set方法将value设置到字段
     * @param bean
     * @param fieldName
     * @param value
     * @throws Exception
     */
    private static void setFieldValue(Object bean, String fieldName, Object value)
            throws Exception {
        StringBuffer result = new StringBuffer();
        String methodName = result.append("set")
                .append(fieldName.substring(0, 1).toUpperCase())
                .append(fieldName.substring(1)).toString();

        /**
         * 利用发射调用bean.set方法将value设置到字段
         */
        Class[] classArr = new Class[1];
        classArr[0]="java.lang.String".getClass();
        Method method=bean.getClass().getMethod(methodName,classArr);
        method.invoke(bean,value);
    }

}
