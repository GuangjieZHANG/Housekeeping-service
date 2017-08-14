/**
 * 
 */
package com.manage.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.manage.action.BaseAction;
import com.manage.po.Removeorder;
import com.manage.po.Servicetype;
import com.manage.po.User;
import com.manage.po.Worker;
import com.manage.util.DateFormat;

/**
 * @author 情愿孤独
 *
 */
public class RemoveorderAdapter extends TypeAdapter<Removeorder> {

	@Override
	public Removeorder read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		final Removeorder removeorder = new Removeorder();
		
		in.beginObject();
		while(in.hasNext()){
			switch (in.nextName()) {
			case "orderId":
				removeorder.setOrderId(in.nextString());
				break;
			case "worker":
				removeorder.setWorker(BaseAction.Json2Object(in.nextString(), Worker.class));
				break;
			case "user":
				removeorder.setUser(BaseAction.Json2Object(in.nextString(), User.class));
				break;
			case "servicetype":
				removeorder.setServicetype(BaseAction.Json2Object(in.nextString(), Servicetype.class));
				break;
			case "orderName":
				removeorder.setOrderName(in.nextString());
				break;
			case "address":
				removeorder.setAddress(in.nextString());
				break;
			case "longitude":
				removeorder.setLongitude(in.nextDouble());
				break;
			case "latitude":
				removeorder.setLatitude(in.nextDouble());
				break;
			case "predictTime":
				removeorder.setPredictTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "removeTime":
				removeorder.setRemoveTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			default:
				break;
			}
		}
		
		in.endObject();
		return removeorder;
	}

	@Override
	public void write(JsonWriter out, Removeorder removeorder) throws IOException {
		// TODO Auto-generated method stub
		out.beginObject();
			out.name("orderId").value(removeorder.getOrderId());
			out.name("worker").jsonValue(BaseAction.Object2json(removeorder.getWorker()));
			out.name("user").jsonValue(BaseAction.Object2json(removeorder.getUser()));
			out.name("servicetype").jsonValue(BaseAction.Object2json(removeorder.getServicetype()));
			out.name("orderName").value(removeorder.getOrderName());
			out.name("address").value(removeorder.getAddress());
			out.name("longitude").value(removeorder.getLongitude());
			out.name("latitude").value(removeorder.getLatitude());
			out.name("predictTime").value(DateFormat.getTimestampToString(removeorder.getPredictTime()));
			out.name("removeTime").value(DateFormat.getTimestampToString(removeorder.getRemoveTime()));
		out.endObject();
	}

}
