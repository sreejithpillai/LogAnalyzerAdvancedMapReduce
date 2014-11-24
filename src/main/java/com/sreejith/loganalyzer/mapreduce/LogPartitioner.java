package com.sreejith.loganalyzer.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogPartitioner extends Partitioner<IntWritable, IntWritable> {

	private static Logger logger = LoggerFactory.getLogger(LogPartitioner.class);
	@Override
	public int getPartition(IntWritable key, IntWritable value, int numReduceTasks) {
		logger.info("Partitioner started");
		
		int intKey=key.get();
		if(intKey>=8 && intKey<=18){
			return 1;			
		}else{
			return 0;
		}
	}
	

}
