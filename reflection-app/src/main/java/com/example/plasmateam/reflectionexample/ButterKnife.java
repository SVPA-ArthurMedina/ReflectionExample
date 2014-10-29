package com.example.plasmateam.reflectionexample;

import android.app.Activity;
import android.util.Log;

import java.lang.reflect.Field;

public class ButterKnife {
  public static void inject(Activity activity) {
    Class<? extends Activity> aClass = activity.getClass();
    Field[] declaredFields = aClass.getDeclaredFields();
    for (Field declaredField : declaredFields) {
      InjectView annotation = declaredField.getAnnotation(InjectView.class);
      if (annotation != null) {
        int value = annotation.value();
        try {
          declaredField.setAccessible(true);
          declaredField.set(activity, activity.findViewById(value));
        } catch (IllegalAccessException e) {
          Log.e("ButterKnife", e.getMessage());
          e.printStackTrace();
        }
      }
    }
  }
}
