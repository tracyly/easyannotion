package com.ymsun.bindapi;

import android.app.Activity;
import android.view.View;

import com.ymsun.annotion.BindId;
import com.ymsun.annotion.OnClick;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Copyright (C) 2018 IFLYTEK Inc., All Rights Reserved.
 *
 * @author: sunyuanming
 * @date: 2018/6/8
 */
public class BindApi {

    public static void init(Activity obj) {
        bindViewId(obj);
        bindOnClick(obj);
    }


    private static  void bindOnClick(final Activity obj) {
      Class<?> cls=obj.getClass();
      Method [] methods=cls.getMethods();
      for(final Method method :methods)
      {
          if(method.isAnnotationPresent(OnClick.class))
          {
              OnClick onClick=method.getAnnotation(OnClick.class);
              int []ids=onClick.value();
              for(int id :ids)
              {
                  Method method2 = null;
                  try {
                      method2 = cls.getMethod("findViewById", int.class);
                  } catch (NoSuchMethodException e) {
                      e.printStackTrace();
                  }
                  method2.setAccessible(true);
                  View v= null;
                  try {
                      v = (View)method2.invoke(obj, id);
                  } catch (IllegalAccessException e) {
                      e.printStackTrace();
                  } catch (InvocationTargetException e) {
                      e.printStackTrace();
                  }

                  v.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          method.setAccessible(true);
                          try {
                              method.invoke(obj,v);
                          } catch (IllegalAccessException e) {
                              e.printStackTrace();
                          } catch (InvocationTargetException e) {
                              e.printStackTrace();
                          }
                      }
                  });
              }
          }
      }
    }

    private static void bindViewId(Activity obj) {
        Class<?> cls = obj.getClass();
        if (cls.isAnnotationPresent(BindId.class)) {
            //这里使用了反射的方法来调用setContentView
            BindId mId = cls.getAnnotation(BindId.class);
            int id = mId.value();
            try {
                Method method = cls.getMethod("setContentView", int.class);
                method.setAccessible(true);
                method.invoke(obj, id);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //这里直接调用findviewbyid
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(BindId.class)) {
                    BindId mId2 = field.getAnnotation(BindId.class);
                    int id2 = mId2.value();
                    try {
                    Method method = cls.getMethod("findViewById", int.class);
                    method.setAccessible(true);
                    View view = (View)method.invoke(obj,id2);
                    field.setAccessible(true);
                        field.set(obj, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

}
