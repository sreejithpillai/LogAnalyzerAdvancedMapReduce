/*
 * Copyright 2014 Sreejith Pillai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
		job.setCombinerClass(LogReducer.class);
		job.setReducerClass(LogReducer.class);
		
		job.setNumReduceTasks(2);
		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
	
		job.waitForCompletion(true);
		
	}

}
