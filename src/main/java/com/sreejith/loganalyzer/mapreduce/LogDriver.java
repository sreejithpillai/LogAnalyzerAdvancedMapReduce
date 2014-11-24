package com.sreejith.loganalyzer.mapreduce;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class LogDriver {
	
	public static void main(String[] args) throws Exception {			
		Job job = new Job();
		job.setJarByClass(LogDriver.class);
		job.setJobName("Log Analyzer");
		
		job.setMapperClass(LogMapper.class);
		job.setPartitionerClass(LogPartitioner.class);
		job.setPartitionerClass(LogPartitioner.class);
		job.setReducerClass(LogReducer.class);
		
		job.setNumReduceTasks(2);
		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
	
		job.waitForCompletion(true);
		
	}

}
