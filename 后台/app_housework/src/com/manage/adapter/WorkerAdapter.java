/**
 * 
 */
package com.manage.adapter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.manage.po.Servicetype;
import com.manage.po.Worker;
import com.manage.util.DateFormat;

/**
 * @author 情愿孤独
 * 
 */
public class WorkerAdapter extends TypeAdapter<Worker> {

	@Override
	public Worker read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		final Worker worker = new Worker();
		
		in.beginObject();
		while(in.hasNext()){
			switch (in.nextName()) {
			case "workerId":
				worker.setWorkerId(in.nextString());
				break;
			case "number":
				worker.setNumber(in.nextString());
				break;
			case "workerName":
				worker.setWorkerName(in.nextString());;
				break;
			case "password":
				worker.setPassword(in.nextString());;
				break;
			case "phoneNumber":
				worker.setPhoneNumber(in.nextString());;
				break;
			case "cardId":
				worker.setCardId(in.nextString());
				break;
			case "age":
				worker.setAge((short) in.nextInt());
				break;
			case "sex":
				worker.setSex(in.nextString());
				break;
			case "brief":
				worker.setBrief(in.nextString());
				break;
			case "address":
				worker.setAddress(in.nextString());
				break;
			case "photo":
				worker.setPhoto(in.nextString());
				break;
			case "longitude":
				worker.setLongitude(in.nextDouble());
				break;
			case "latitude":
				worker.setLatitude(in.nextDouble());
				break;
			case "addtime":
				worker.setAddtime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "modifyTime":
				worker.setModifyTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "servicetypes":
				in.beginArray();
				Set<Servicetype> servicetypes = new HashSet<Servicetype>();
				while (in.hasNext()) {
					in.beginObject();
					final Servicetype servicetype = new Servicetype();
					while(in.hasNext()){
						switch (in.nextName()) {
						case "serviceTypeId":
							servicetype.setServiceTypeId(in.nextString());
							break;
						case "serviceTypeName":
							servicetype.setServiceTypeName(in.nextString());
							break;
						case "description":
							servicetype.setDescription(in.nextString());;
							break;
						case "userPrice":
							servicetype.setUserPrice(in.nextDouble());;
							break;
						case "workerPrice":
							servicetype.setWorkerPrice(in.nextDouble());
							break;
						}
					}
					servicetypes.add(servicetype);
					in.endObject();
				}
				worker.setServicetypes(servicetypes);
				in.endArray();
				break;
			default:
				break;
			}
		}
		
		in.endObject();
		return worker;
	}

	@Override
	public void write(JsonWriter out, Worker worker) throws IOException {
		// TODO Auto-generated method stub
		out.beginObject();
			out.name("workerId").value(worker.getWorkerId());
			out.name("number").value(worker.getNumber());
			out.name("workerName").value(worker.getWorkerName());
			out.name("phoneNumber").value(worker.getPhoneNumber());
			out.name("cardId").value(worker.getCardId());
			out.name("age").value(worker.getAge());
			out.name("sex").value(worker.getSex());
			out.name("brief").value(worker.getBrief());
			out.name("address").value(worker.getAddress());
			out.name("longitude").value(worker.getLongitude());
			out.name("latitude").value(worker.getLatitude());
			out.name("addtime").value(DateFormat.getTimestampToString(worker.getAddtime()));
			out.name("servicetypes").beginArray();
				for(Servicetype st : worker.getServicetypes()){
					out.beginObject();
						out.name("serviceTypeId").value(st.getServiceTypeId());
						out.name("serviceTypeName").value(st.getServiceTypeName());
						out.name("description").value(st.getDescription());
						out.name("userPrice").value(st.getUserPrice());
						out.name("workerPrice").value(st.getWorkerPrice());
					out.endObject();
				}
			out.endArray();
		
		out.endObject();
	}

}
