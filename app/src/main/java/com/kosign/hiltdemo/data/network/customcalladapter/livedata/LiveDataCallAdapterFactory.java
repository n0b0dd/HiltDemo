package com.kosign.hiltdemo.data.network.customcalladapter.livedata;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.kosign.hiltdemo.data.network.ApiResponse;
import com.kosign.hiltdemo.data.network.Resource;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class LiveDataCallAdapterFactory extends CallAdapter.Factory {

    public static LiveDataCallAdapterFactory create(){
        return new LiveDataCallAdapterFactory();
    }

    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {

        if (getRawType(returnType) != LiveData.class) {
            return null;
        }
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class<?> rawObservableType = getRawType(observableType);
        if (rawObservableType != Resource.class) {
            throw new IllegalArgumentException("type must be a resource");
        }
        if (!(observableType instanceof ParameterizedType)) {
            throw new IllegalArgumentException("resource must be parameterized");
        }
        Type bodyType = getParameterUpperBound(0, (ParameterizedType) observableType);

//        ParameterizedType observableType = (ParameterizedType) CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) returnType);
        return new LiveDataCallAdapter<>(bodyType);
    }
}
