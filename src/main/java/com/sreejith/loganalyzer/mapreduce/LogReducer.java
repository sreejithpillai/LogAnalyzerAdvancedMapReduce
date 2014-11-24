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

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogReducer extends
		Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

	private static Logger logger = LoggerFactory.getLogger(LogReducer.class);
	
	public void reduce(IntWritable key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {

		logger.info("Reducer started");
		int sum = 0;
		for (IntWritable value : values) {
			sum = sum + value.get();
		}
		context.write(key, new IntWritable(sum));
		logger.info("Reducer completed");

	}
}
