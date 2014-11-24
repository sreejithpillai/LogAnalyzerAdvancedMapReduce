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
