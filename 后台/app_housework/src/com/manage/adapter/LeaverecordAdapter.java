/**
 * 
 */
package com.manage.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.manage.action.BaseAction;
import com.manage.po.Leaverecord;
import com.manage.po.Worker;
import com.manage.util.DateFormat;

/**
 * @author 情愿孤独
 *
 */
public class LeaverecordAdapter extends TypeAdapter<Leaverecord> {

	@Override
	public Leaverecord read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		final Leaverecord leaverecord = new Leaverecord();
		
		in.beginObject();
		while(in.hasNext()){
			switch (in.nextName()) {
			case "leaveId":
				leaverecord.setLeaveId(in.nextString());
				break;
			case "worker":
				leaverecord.setWorker(BaseAction.Json2Object(in.nextString(), Worker.class));
				break;
			case "beginTime":
				leaverecord.setBeginTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "endTime":
				leaverecord.setEndTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			case "description":
				leaverecord.setDescription(in.nextString());;
				break;
			case "addTime":
				leaverecord.setAddTime(DateFormat.getTimeStampByString(in.nextString()));
				break;
			}
		}
		in.endObject();
		return leaverecord;
	}

	@Override
	public void write(JsonWriter out, Leaverecord leaverecord) throws IOException {
		// TODO Auto-generated method stub
		out.beginObject();
			out.name("leaveId").value(leaverecord.getLeaveId());
			out.name("worker").jsonValue(BaseAction.Object2json(leaverecord.getWorker()));		
			out.name("beginTime").value(DateFormat.getTimestampToString(leaverecord.getBeginTime()));
			out.name("endTime").value(DateFormat.getTimestampToString(leaverecord.getEndTime()));
			out.name("description").value(leaverecord.getDescription());
			out.name("addTime").value(DateFormat.getTimestampToString(leaverecord.getAddTime()));
		out.endObject();
	}

}
