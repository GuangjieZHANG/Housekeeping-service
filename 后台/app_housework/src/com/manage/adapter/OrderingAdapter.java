/**
 * 
 */
package com.manage.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.manage.action.BaseAction;
import com.manage.po.Ordering;
import com.manage.po.Servicetype;
import com.manage.po.User;
import com.manage.po.Worker;
import com.manage.util.DateFormat;

/**
 * @author 情愿孤独
 *
 */
public class OrderingAdapter extends TypeAdapter<Ordering> {

	@Override
	public Ordering read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		final Ordering ordering = new Ordering();
		
		in.beginObject();
		while(in.hasNext()){
			switch (in.nextName()) {
			case "orderId":
				ordering.setOrderId(in.nextString());
				break;
			case "worker":
				ordering.setWorker(BaseAction.Json2Object(in.nextString(), Worker.class));
				break;
			case "user":
				ordering.setUser(BaseAction.Json2Object(in.nextString(), User.class));
				break;
			case "servicetype":
				ordering.setServicetype(BaseAction.Json2Object(in.nextString(), Servicetype.class));
				break;
			case "orderName":
				ordering.setOrderName(in.nextString());
				break;
			case "address":
				ordering.setAddress(in.nextString());
				break;
			case "longitude":
				ordering.setLongitude(in.nextDouble());
				break;
			case "latitude":
				ordering.setLatitude(in.nextDouble());
				break;
			case "predictTime":
				ordering.setPredictTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "startTime":
				ordering.setStartTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "addTime":
				ordering.setAddTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			default:
				break;
			}
		}
		
		in.endObject();
		return ordering;
	}

	@Override
	public void write(JsonWriter out, Ordering ordering) throws IOException {
		// TODO Auto-generated method stub
		out.beginObject();
			out.name("orderId").value(ordering.getOrderId());
			out.name("worker").jsonValue(BaseAction.Object2json(ordering.getWorker()));
			out.name("user").jsonValue(BaseAction.Object2json(ordering.getUser()));
			out.name("servicetype").jsonValue(BaseAction.Object2json(ordering.getServicetype()));
			out.name("orderName").value(ordering.getOrderName());
			out.name("address").value(ordering.getAddress());
			out.name("longitude").value(ordering.getLongitude());
			out.name("latitude").value(ordering.getLatitude());
			out.name("predictTime").value(DateFormat.getTimestampToString(ordering.getPredictTime()));
			out.name("startTime").value(DateFormat.getTimestampToString(ordering.getStartTime()));
			out.name("addTime").value(DateFormat.getTimestampToString(ordering.getAddTime()));
		out.endObject();
	}

}
