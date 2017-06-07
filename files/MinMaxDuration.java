package com.xavient;

import org.apache.hadoop.io.Writable;

import org.apache.hadoop.io.*;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class MinMaxDuration implements Writable {

	Integer minDuration;
	Integer maxDuration;
	
	public MinMaxDuration() {
		minDuration=0;
		maxDuration=0;
	}
	
	void setMinDuration(Integer duration){
		this.minDuration=duration;
	}
	void setMaxDuration(Integer duration){
		this.maxDuration=duration;
	}
	
	Integer getMinDuration() {
		return minDuration;
	}
	Integer getMaxDuration(){
		return maxDuration;
	}
	
	
			public void write(DataOutput out) throws IOException {
			
				out.writeInt(minDuration);
				out.writeInt(maxDuration);
		}
		 
		 
		public void readFields(DataInput in) throws IOException {
			minDuration=new Integer(in.readInt());
			maxDuration=new Integer(in.readInt());
		}

		public String toString() {
			return minDuration + "\t" + maxDuration;
		}

}
