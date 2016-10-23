package cqupt.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonUtils {
	public static <T> T getEntity(String str, Class<T> classOfT) {
		Gson gson = new Gson();
		return gson.fromJson(str, classOfT);
	}
	
	public static String EntityToJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
	public static <T> T getEntityList(String str, Type typeOfT) {
		Gson gson = new Gson();
		return gson.fromJson(str, typeOfT);
	}
	
	public static JsonObject getJsonObject() {
		return new JsonObject();
	}
	
	public static JsonObject parseJson(String str) {
		JsonParser parser = new JsonParser();
		return (JsonObject) parser.parse(str);
	}
}
