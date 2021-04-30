package com.ems.common.captch;

public class CaptchaServiceSingleton {

//	   private static CaptchaServiceSingleton INSTANCE;
//	   private static Throwable thrownLoadingThisClass;
//	   private ImageCaptchaService imgcapa;
//
//
//	    static {
//	       // 모든 예외를 잡아내야 한다.
//	       try {
//	           INSTANCE = new CaptchaServiceSingleton();
//	       } catch (Throwable t) {
//	           thrownLoadingThisClass = t;
//	           t.printStackTrace(System.err);
//	       }
//	   }
//
//	   public static CaptchaServiceSingleton getInstance() {
//	       if (thrownLoadingThisClass == null) {
//	           return INSTANCE;
//	       } else {
//	           throw new IllegalStateException(
//	               "This class has not initialized properly. Check the root cause."
//	               , thrownLoadingThisClass);
//	       }
//	   }
//
//	   private CaptchaServiceSingleton() {
//	      // 초기화 작업...
//
//		   SimpleListImageCaptchaEngine engine = new SimpleListImageCaptchaEngine();
//
//		   imgcapa =  (ImageCaptchaService)new DefaultManageableImageCaptchaService((CaptchaStore)new FastHashMapCaptchaStore(), (CaptchaEngine)engine, 180, 100000, 75000);
//
//
//	   }
//	   // ... 나머지 코드
//
//
//	   public ImageCaptchaService getImgcapa() {
//	        return imgcapa;
//	    }


	}
