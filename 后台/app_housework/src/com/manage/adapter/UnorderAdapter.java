/**
 * 
 */
package com.manage.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.manage.action.BaseAction;
import com.manage.po.Servicetype;
import com.manage.po.Unorder;
import com.manage.po.User;
import com.manage.po.Worker;
import com.manage.util.DateFormat;

/**
 * @author 情愿孤独
 *
 */
public class UnorderAdapter extends TypeAdapter<Unorder> {

	@Override
	public Unorder read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		final Unorder unorder = new Unorder();
		
		in.beginObject();
		while(in.hasNext()){
			switch (in.nextName()) {
			case "orderId":
				unorder.setOrderId(in.nextString());
				break;
			case "worker":
				unorder.setWorker(BaseAction.Json2Object(in.nextString(), Worker.class));
				break;
			case "user":
				unorder.setUser(BaseAction.Json2Object(in.nextString(), User.class));
				break;
			case "servicetype":
				unorder.setServicetype(BaseAction.Json2Object(in.nextString(), Servicetype.class));
				break;
			case "orderName":
				unorder.setOrderName(in.nextString());
				break;
			case "address":
				unorder.setAddress(in.nextString());
				break;
			case "longitude":
				unorder.setLongitude(in.nextDouble());
				break;
			case "latitude":
				unorder.setLatitude(in.nextDouble());
				break;
			case "isReced":
				unorder.setIsReced(in.nextBoolean());
				break;
			case "predictTime":
				unorder.setPredictTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "timer":
				unorder.setTimer(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "addTime":
				unorder.setAddTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "modifyTime":
				unorder.setModifyTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			default:
				break;
			}
		}
		
		in.endObject();
		return unorder;
	}

	@Override
	public void write(JsonWriter out, Unorder unorder) throws IOException {
		// TODO Auto-generated method stub
		out.beginObject();
			out.name("orderId").value(unorder.getOrderId());
			out.name("worker").jsonValue(BaseAction.Object2json(unorder.getWorker()));
			out.name("user").jsonValue(BaseAction.Object2json(unorder.getUser()));
			out.name("servicetype").jsonValue(BaseAction.Object2json(unorder.getServicetype()));
			out.name("orderName").value(unorder.getOrderName());
			out.name("address").value(unorder.getAddress());
			out.name("longitude").value(unorder.getLongitude());
			out.name("latitude").value(unorder.getLatitude());
			out.name("isReced").value(unorder.getIsReced());
			out.name("predictTime").value(DateFormat.getTimestampToString(unorder.getPredictTime()));
			out.name("addTime").value(DateFormat.getTimestampToString(unorder.getAddTime()));
		out.endObject();
	}

}
