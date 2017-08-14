/**
 * 
 */
package com.manage.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.manage.po.User;
import com.manage.util.DateFormat;

/**
 * @author 情愿孤独
 *
 */
public class UserAdapter extends TypeAdapter<User> {

	@Override
	public User read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		final User user = new User();
		
		in.beginObject();
		while(in.hasNext()){
			switch (in.nextName()) {
			case "userId":
				user.setUserId(in.nextString());
				break;
			case "userName":
				user.setUserName(in.nextString());;
				break;
			case "phoneNumber":
				user.setPhoneNumber(in.nextString());;
				break;
			case "password":
				user.setPassword(in.nextString());
				break;
			case "address":
				user.setAddress(in.nextString());
				break;
			case "photo":
				user.setPhoto(in.nextString());
				break;
			case "longitude":
				user.setLongitude(in.nextDouble());
				break;
			case "latitude":
				user.setLatitude(in.nextDouble());
				break;
			case "addTime":
				user.setAddTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "modifyTime":
				user.setModifyTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			}
		}
		in.endObject();
		return user;
	}

	@Override
	public void write(JsonWriter out, User user) throws IOException {
		// TODO Auto-generated method stub
		out.beginObject();
			out.name("userId").value(user.getUserId());
			out.name("userName").value(user.getUserName());
			out.name("phoneNumber").value(user.getPhoneNumber());
			out.name("address").value(user.getAddress());
			out.name("longitude").value(user.getLongitude());
			out.name("latitude").value(user.getLatitude());
		out.endObject();
	}
}
