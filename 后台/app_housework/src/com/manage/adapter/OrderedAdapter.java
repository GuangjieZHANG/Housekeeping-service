/**
 * 
 */
package com.manage.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.manage.action.BaseAction;
import com.manage.po.Ordered;
import com.manage.po.Servicetype;
import com.manage.po.User;
import com.manage.po.Worker;
import com.manage.util.DateFormat;

/**
 * @author 情愿孤独
 *
 */
public class OrderedAdapter extends TypeAdapter<Ordered> {

	@Override
	public Ordered read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		final Ordered ordered = new Ordered();
		
		in.beginObject();
		while(in.hasNext()){
			switch (in.nextName()) {
			case "orderId":
				ordered.setOrderId(in.nextString());
				break;
			case "worker":
				ordered.setWorker(BaseAction.Json2Object(in.nextString(), Worker.class));
				break;
			case "user":
				ordered.setUser(BaseAction.Json2Object(in.nextString(), User.class));
				break;
			case "servicetype":
				ordered.setServicetype(BaseAction.Json2Object(in.nextString(), Servicetype.class));
				break;
			case "orderName":
				ordered.setOrderName(in.nextString());
				break;
			case "address":
				ordered.setAddress(in.nextString());
				break;
			case "longitude":
				ordered.setLongitude(in.nextDouble());
				break;
			case "latitude":
				ordered.setLatitude(in.nextDouble());
				break;
			case "predictTime":
				ordered.setPredictTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "startTime":
				ordered.setStartTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "finishTime":
				ordered.setFinishTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "isPaid":
				ordered.setIsPaid(in.nextBoolean());
				break;
			case "cost":
				ordered.setCost(in.nextDouble());
				break;
			case "addTime":
				ordered.setAddTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			default:
				break;
			}
		}
		
		in.endObject();
		return ordered;
	}

	@Override
	public void write(JsonWriter out, Ordered ordered) throws IOException {
		// TODO Auto-generated method stub
		out.beginObject();
			out.name("orderId").value(ordered.getOrderId());
			out.name("worker").jsonValue(BaseAction.Object2json(ordered.getWorker()));
			out.name("user").jsonValue(BaseAction.Object2json(ordered.getUser()));
			out.name("servicetype").jsonValue(BaseAction.Object2json(ordered.getServicetype()));
			out.name("orderName").value(ordered.getOrderName());
			out.name("address").value(ordered.getAddress());
			out.name("longitude").value(ordered.getLongitude());
			out.name("latitude").value(ordered.getLatitude());
			out.name("predictTime").value(DateFormat.getTimestampToString(ordered.getPredictTime()));
			out.name("startTime").value(DateFormat.getTimestampToString(ordered.getStartTime()));
			out.name("finishTime").value(DateFormat.getTimestampToString(ordered.getFinishTime()));
			out.name("isPaid").value(ordered.getIsPaid());
			out.name("cost").value(ordered.getCost());
			out.name("addTime").value(DateFormat.getTimestampToString(ordered.getAddTime()));
		out.endObject();
	}

}
