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
public class ServicetypeAdapter extends TypeAdapter<Servicetype> {

	@Override
	public Servicetype read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		final Servicetype servicetype = new Servicetype();
		
		in.beginObject();
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
			case "addtime":
				servicetype.setAddtime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "modifyTime":
				servicetype.setModifyTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "workers":
				in.beginArray();
				Set<Worker> workers = new HashSet<Worker>();
				while (in.hasNext()) {
					in.beginObject();
					final Worker worker = new Worker();
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
						case "longitude":
							worker.setLongitude(in.nextDouble());
							break;
						case "latitude":
							worker.setLatitude(in.nextDouble());
							break;
						case "addtime":
							worker.setAddtime(DateFormat.getTimeStampByString(in.nextString()));
							break;
						}
					}
					workers.add(worker);
					in.endObject();
				}
				servicetype.setWorkers(workers);
				in.endArray();
				break;
			default:
				break;
			}
		}
		
		in.endObject();
		return servicetype;
	}

	@Override
	public void write(JsonWriter out, Servicetype servicetype) throws IOException {
		// TODO Auto-generated method stub
		out.beginObject();
			out.name("serviceTypeId").value(servicetype.getServiceTypeId());
			out.name("serviceTypeName").value(servicetype.getServiceTypeName());
			out.name("description").value(servicetype.getDescription());
			out.name("userPrice").value(servicetype.getUserPrice());
			out.name("workerPrice").value(servicetype.getWorkerPrice());
			
			out.name("workers").beginArray();
				for(Worker worker : servicetype.getWorkers()){
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
					out.endObject();
				}
			out.endArray();
			
		out.endObject();
	}

}
